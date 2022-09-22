package com.ironsource.ui

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.ironsource.domain.usecase.GetActionError
import com.ironsource.ui.databinding.ActivityWelcomeBinding
import com.ironsource.ui.di.WelcomeFeatureComponentViewModel
import com.ironsource.ui.mvi.WelcomeEvent
import com.ironsource.ui.mvi.WelcomeState
import com.ironsource.ui.viewmodel.WelcomeViewModel
import com.ironsource.ui.viewmodel.WelcomeViewModelFactory
import javax.inject.Inject

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    @Inject
    lateinit var viewModelFactory: WelcomeViewModelFactory
    private lateinit var viewModel: WelcomeViewModel

    companion object {
        private const val PICK_CONTACT = 1
        private const val PERMISSIONS_REQUEST_READ_CONTACTS = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        diSetup()
        viewModel = ViewModelProvider(this, viewModelFactory).get(WelcomeViewModel::class.java)

        binding.actionButton.setOnClickListener {
            viewModel.obtainEvent(
                event = WelcomeEvent.OnClickActionButton
            )
        }

        viewModel.stateLiveData.observe(this) { state ->
            render(state)
        }
    }

    private fun render(state: WelcomeState) {
        when (state) {
            is WelcomeState.Error -> {
                Toast.makeText(this, state.error.getErrorMessage(), Toast.LENGTH_SHORT).show()
            }
            is WelcomeState.Animation -> {
                binding.actionButton.startAnimation(
                    AnimationUtils.loadAnimation(
                        this,
                        R.anim.rotation
                    )
                )
            }
            is WelcomeState.Toast -> {
                Toast.makeText(this, "Action is Toast!", Toast.LENGTH_SHORT).show()
            }
            is WelcomeState.Call -> {
                if (checkPermissions()) {
                    val intent = Intent(
                        Intent.ACTION_PICK,
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                    )
                    startActivityForResult(intent, PICK_CONTACT)
                }
            }
            is WelcomeState.Notification -> {
                //TODO: show notification
                Toast.makeText(this, "I'm a notification", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_CONTACT && resultCode == Activity.RESULT_OK) {
            try {
                val number = readContactNumber(data?.data)
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:$number")
                startActivity(callIntent)
            } catch (e: Exception) {
                Toast.makeText(
                    this,
                    resources.getString(R.string.no_contact_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.contains(0)) {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                )
                startActivityForResult(intent, PICK_CONTACT)
            }
        }
    }

    private fun checkPermissions(): Boolean {
        return when (ContextCompat.checkSelfPermission(
            this, android.Manifest.permission.CALL_PHONE
        )) {
            PackageManager.PERMISSION_GRANTED -> {
                true
            }
            PackageManager.PERMISSION_DENIED -> {
                requestPermissions(arrayOf(android.Manifest.permission.CALL_PHONE), PERMISSIONS_REQUEST_READ_CONTACTS)
                false
            }
            else -> false
        }
    }

    private fun readContactNumber(contactData: Uri?): String {
        contactData?.let { uri ->
            val cursor: Cursor? = contentResolver.query(
                uri,
                null,
                null,
                null,
                null
            )
            cursor?.let {
                if (it.moveToFirst()) {
                    return it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                }
            }
        }
        return ""
    }

    private fun GetActionError.getErrorMessage(): String {
        return when (this) {
            GetActionError.NoActions -> resources.getString(R.string.no_actions_error)
            GetActionError.NoTypeRecognized -> resources.getString(R.string.no_type_recognized)
        }
    }

    private fun diSetup() {
        ViewModelProvider(this).get(WelcomeFeatureComponentViewModel::class.java)
            .welcomeFeatureComponent
            .inject(this)
    }
}
package com.ironsource.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
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
        when(state) {
            is WelcomeState.Loading -> {
                //TODO: show loading
            }
            is WelcomeState.Error -> {
                //TODO: show error
            }
            is WelcomeState.Animation -> {
                //TODO: show animation
            }
            is WelcomeState.Toast -> {
                //TODO: show toast
            }
            is WelcomeState.Call -> {
                //TODO: make a call
            }
            is WelcomeState.Notification -> {
                //TODO: show notification
            }
        }
    }

    private fun diSetup() {
        ViewModelProvider(this).get(WelcomeFeatureComponentViewModel::class.java)
            .welcomeFeatureComponent
            .inject(this)
    }
}
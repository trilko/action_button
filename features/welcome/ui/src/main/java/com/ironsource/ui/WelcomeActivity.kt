package com.ironsource.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ironsource.ui.databinding.ActivityWelcomeBinding
import com.ironsource.ui.di.WelcomeFeatureComponentViewModel
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
    }

    private fun diSetup() {
        ViewModelProvider(this).get(WelcomeFeatureComponentViewModel::class.java)
            .welcomeFeatureComponent
            .inject(this)
    }
}
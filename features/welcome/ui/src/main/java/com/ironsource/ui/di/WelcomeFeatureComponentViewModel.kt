package com.ironsource.ui.di

import androidx.lifecycle.ViewModel
import com.ironsource.ui.di.deps.WelcomeFeatureDepsProvider

class WelcomeFeatureComponentViewModel : ViewModel() {

    val welcomeFeatureComponent = DaggerWelcomeFeatureComponent
        .builder()
        .deps(WelcomeFeatureDepsProvider.deps)
        .build()
}
package com.ironsource.action_button

import android.app.Application
import com.ironsource.action_button.di.AppComponent
import com.ironsource.action_button.di.DaggerAppComponent
import com.ironsource.ui.di.deps.WelcomeFeatureDepsProvider

class App : Application() {

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .context(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
        WelcomeFeatureDepsProvider.deps = appComponent
    }
}
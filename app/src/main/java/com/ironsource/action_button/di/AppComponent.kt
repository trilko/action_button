package com.ironsource.action_button.di

import android.app.Application
import com.ironsource.action_button.App
import com.ironsource.action_button.di.binds.AppBinds
import com.ironsource.ui.di.deps.WelcomeFeatureDeps
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [


        AppBinds::class
    ]
)
interface AppComponent : WelcomeFeatureDeps {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: App)
}
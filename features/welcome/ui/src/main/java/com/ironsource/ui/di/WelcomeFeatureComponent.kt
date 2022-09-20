package com.ironsource.ui.di

import com.ironsource.ui.WelcomeActivity
import com.ironsource.ui.di.deps.WelcomeFeatureDeps
import dagger.Component

@Component(
    dependencies = [
        WelcomeFeatureDeps::class
    ]
)
interface WelcomeFeatureComponent {

    @Component.Builder
    interface Builder {

        fun deps(welcomeFeatureDeps: WelcomeFeatureDeps): Builder

        fun build(): WelcomeFeatureComponent
    }

    fun inject(activity: WelcomeActivity)
}
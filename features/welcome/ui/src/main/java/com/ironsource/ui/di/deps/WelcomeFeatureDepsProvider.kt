package com.ironsource.ui.di.deps

import kotlin.properties.Delegates

interface WelcomeFeatureDepsProvider {

    var deps: WelcomeFeatureDeps

    companion object : WelcomeFeatureDepsProvider by WelcomeFeatureDepsStore
}

object WelcomeFeatureDepsStore : WelcomeFeatureDepsProvider {

    override var deps: WelcomeFeatureDeps by Delegates.notNull()
}
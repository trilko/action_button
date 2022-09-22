package com.ironsource.ui.di.deps

import com.ironsource.domain.usecase.GetActionUseCase

interface WelcomeFeatureDeps {

    val getActionUseCase: GetActionUseCase
}
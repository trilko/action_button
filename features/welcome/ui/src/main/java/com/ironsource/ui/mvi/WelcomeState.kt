package com.ironsource.ui.mvi

import com.ironsource.domain.usecase.GetActionError

sealed class WelcomeState {
    class Error(val error: GetActionError) : WelcomeState()
    object Animation : WelcomeState()
    object Toast : WelcomeState()
    object Call : WelcomeState()
    object Notification : WelcomeState()
}

package com.ironsource.ui.mvi

sealed class WelcomeState {
    object Loading : WelcomeState()
    object Error : WelcomeState()
    object Animation: WelcomeState()
    object Toast: WelcomeState()
    object Call: WelcomeState()
    object Notification: WelcomeState()
}

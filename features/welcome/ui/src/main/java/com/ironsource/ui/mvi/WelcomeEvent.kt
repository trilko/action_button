package com.ironsource.ui.mvi

sealed class WelcomeEvent {
    object OnClickActionButton: WelcomeEvent()
}

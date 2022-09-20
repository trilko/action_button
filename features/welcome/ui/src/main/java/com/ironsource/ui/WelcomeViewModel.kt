package com.ironsource.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class WelcomeViewModel : ViewModel() {

}

class WelcomeViewModelFactory @Inject constructor(

) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WelcomeViewModel() as T
    }
}
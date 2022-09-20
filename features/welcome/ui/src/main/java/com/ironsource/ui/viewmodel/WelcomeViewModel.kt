package com.ironsource.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ironsource.domain.usecase.GetActionUseCase
import com.ironsource.ui.mvi.WelcomeEvent
import com.ironsource.ui.mvi.WelcomeState
import javax.inject.Inject

class WelcomeViewModel(
    private val getActionUseCase: GetActionUseCase
) : ViewModel() {

    private val _stateLiveData = MutableLiveData<WelcomeState>()
    val stateLiveData: LiveData<WelcomeState> = _stateLiveData

    fun obtainEvent(event: WelcomeEvent) {
        when (event) {
            is WelcomeEvent.OnClickActionButton -> {
                val result = getActionUseCase.invoke()
                val currentTime = System.currentTimeMillis()
            }
        }
    }
}

class WelcomeViewModelFactory @Inject constructor(
    private val getActionUseCase: GetActionUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WelcomeViewModel(
            getActionUseCase
        ) as T
    }
}
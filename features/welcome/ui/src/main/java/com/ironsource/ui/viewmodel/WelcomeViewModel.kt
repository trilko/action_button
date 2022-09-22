package com.ironsource.ui.viewmodel

import androidx.lifecycle.*
import com.ironsource.domain.model.Action
import com.ironsource.domain.usecase.GetActionError
import com.ironsource.domain.usecase.GetActionUseCase
import com.ironsource.domain.usecase.GetActionUseCaseResult
import com.ironsource.ui.mvi.WelcomeEvent
import com.ironsource.ui.mvi.WelcomeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WelcomeViewModel(
    private val getActionUseCase: GetActionUseCase
) : ViewModel() {

    private val _stateLiveData = MutableLiveData<WelcomeState>()
    val stateLiveData: LiveData<WelcomeState> = _stateLiveData

    fun obtainEvent(event: WelcomeEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (event) {
                is WelcomeEvent.OnClickActionButton -> {
                    when (val result = getActionUseCase.invoke()) {
                        is GetActionUseCaseResult.Success -> {
                            _stateLiveData.postValue(result.action.mapToWelcomeState())
                        }
                        is GetActionUseCaseResult.Error -> {
                            _stateLiveData.postValue(WelcomeState.Error(result.error))
                        }
                    }
                }
            }
        }
    }

    private fun Action.mapToWelcomeState(): WelcomeState {
        return when (this.type) {
            "animation" -> WelcomeState.Animation
            "toast" -> WelcomeState.Toast
            "call" -> WelcomeState.Call
            "notification" -> WelcomeState.Notification
            else -> WelcomeState.Error(GetActionError.NoTypeRecognized)
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
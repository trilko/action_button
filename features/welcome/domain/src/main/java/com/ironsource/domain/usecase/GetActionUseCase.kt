package com.ironsource.domain.usecase

import com.ironsource.domain.model.Action
import com.ironsource.domain.repository.ActionRepository

class GetActionUseCase(
    private val actionRepository: ActionRepository
) {

    fun invoke(): GetActionUseCaseResult {
        return try {
            val actions = actionRepository.getActions()
            GetActionUseCaseResult.Success(actions[0])
        } catch (e: Exception) {
            GetActionUseCaseResult.Error(GetActionError.NoActions)
        }
    }
}

sealed class GetActionUseCaseResult {
    class Success(action: Action) : GetActionUseCaseResult()
    class Error(error: GetActionError) : GetActionUseCaseResult()
}

sealed class GetActionError {
    object NoActions : GetActionError()
}
package com.ironsource.domain.usecase

import com.ironsource.domain.model.Action
import com.ironsource.domain.model.ActionsContainer
import com.ironsource.domain.repository.ActionCoolDownRepository
import com.ironsource.domain.repository.ActionRepository
import com.ironsource.domain.verifiers.VerifiersFactory

class GetActionUseCase(
    private val actionRepository: ActionRepository,
    private val verifiersFactory: VerifiersFactory,
    private val saveActionCoolDownRepository: ActionCoolDownRepository
) {

    fun invoke(): GetActionUseCaseResult {
        return try {
            var actionsContainer = actionRepository.getActions()
            val verifiers = verifiersFactory.createVerifiers()

            for (verifier in verifiers) {
                actionsContainer = verifier.removeActions(actionsContainer)
            }

            if (actionsContainer.get().isEmpty()) {
                GetActionUseCaseResult.Error(GetActionError.NoActions)
            } else {
                val priorityAction = actionsContainer.getPriorityAction()
                saveActionCoolDownRepository.saveActionCoolDown(priorityAction)
                GetActionUseCaseResult.Success(priorityAction)
            }
        } catch (e: Exception) {
            GetActionUseCaseResult.Error(GetActionError.NoActions)
        }
    }
}

sealed class GetActionUseCaseResult {
    class Success(val action: Action) : GetActionUseCaseResult()
    class Error(val error: GetActionError) : GetActionUseCaseResult()
}

sealed class GetActionError {
    object NoActions : GetActionError()
}
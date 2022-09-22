package com.ironsource.data.verifiers

import com.ironsource.data.internet_availability.InternetAvailable
import com.ironsource.domain.model.Action
import com.ironsource.domain.model.ActionsContainer
import com.ironsource.domain.verifiers.Verifier

class InternetVerifier(
    private val internetAvailable: InternetAvailable
) : Verifier {

    companion object {
        private const val TOAST_ACTION_TYPE = "toast"
    }

    override fun removeActions(actionsContainer: ActionsContainer): ActionsContainer {
        val actions = ArrayList<Action>()
        val isInternetAvailable = internetAvailable.check()
        for (action in actionsContainer.get()) {
            val isToastAction = action.type == TOAST_ACTION_TYPE
            if (!isToastAction) {
                actions.add(action)
            } else {
                if (isInternetAvailable) {
                    actions.add(action)
                }
            }
        }
        return ActionsContainer(actions)
    }
}
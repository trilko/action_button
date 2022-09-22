package com.ironsource.data.verifiers

import com.ironsource.domain.model.Action
import com.ironsource.domain.model.ActionsContainer
import com.ironsource.domain.verifiers.Verifier

class EnabledVerifier : Verifier {

    override fun removeActions(actionsContainer: ActionsContainer): ActionsContainer {
        val actions = ArrayList<Action>()
        for (action in actionsContainer.get()) {
            if (action.enabled) {
                actions.add(action)
            }
        }
        return ActionsContainer(actions)
    }
}
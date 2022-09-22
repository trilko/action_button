package com.ironsource.data.verifiers

import com.ironsource.data.cool_down.ActionsCoolDown
import com.ironsource.domain.model.Action
import com.ironsource.domain.model.ActionsContainer
import com.ironsource.domain.verifiers.Verifier

class CoolDownVerifier(
    private val actionsCoolDown: ActionsCoolDown
) : Verifier {

    override fun removeActions(actionsContainer: ActionsContainer): ActionsContainer {
        val actions = ArrayList<Action>()
        for (action in actionsContainer.get()) {
            if (actionsCoolDown.isActionAvailable(action)) {
                actions.add(action)
            }
        }
        return ActionsContainer(actions)
    }
}
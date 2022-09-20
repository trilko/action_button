package com.ironsource.data.verifiers

import com.ironsource.data.current_day.CurrentDay
import com.ironsource.domain.model.Action
import com.ironsource.domain.model.ActionsContainer
import com.ironsource.domain.verifiers.Verifier

class ValidDaysVerifier(
    private val currentDay: CurrentDay
) : Verifier {

    override fun removeActions(actionsContainer: ActionsContainer): ActionsContainer {
        val actions = ArrayList<Action>()
        for (action in actionsContainer.get()) {
            if (action.valid_days.contains(currentDay.get())) {
                actions.add(action)
            }
        }
        return ActionsContainer(actions)
    }
}
package com.ironsource.domain.model

data class ActionsContainer(
    private val actions: ArrayList<Action>
) {

    companion object {
        private const val DEFAULT_PRIORITY_VALUE = 0
        private const val LIST_SIZE_WITH_ONE_ELEMENT = 1
        private const val FIRST_ELEMENT_INDEX = 0
    }

    fun get(): List<Action> {
        return actions
    }

    fun remove(action: Action) {
        actions.remove(action)
    }

    fun getPriorityAction(): Action {
        val highestPriorityAction = getHighestPriority()
        val actionsWithHighestPriority = actions.filter { it.priority == highestPriorityAction }

        return if (actionsWithHighestPriority.size == LIST_SIZE_WITH_ONE_ELEMENT) {
            actionsWithHighestPriority[FIRST_ELEMENT_INDEX]
        } else {
            actionsWithHighestPriority.random()
        }
    }

    private fun getHighestPriority(): Int {
        var highestPriority = DEFAULT_PRIORITY_VALUE

        for (action in actions) {
            if (action.priority > highestPriority) {
                highestPriority = action.priority
            } else if (action.priority == highestPriority) {
                highestPriority = action.priority
            }
        }
        return highestPriority
    }
}

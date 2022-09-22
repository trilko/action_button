package com.ironsource.domain.verifiers

import com.ironsource.domain.model.ActionsContainer

interface Verifier {

    fun removeActions(actionsContainer: ActionsContainer): ActionsContainer
}
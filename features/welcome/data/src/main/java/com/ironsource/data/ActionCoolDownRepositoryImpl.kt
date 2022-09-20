package com.ironsource.data

import com.ironsource.data.cool_down.ActionsCoolDown
import com.ironsource.domain.model.Action
import com.ironsource.domain.repository.ActionCoolDownRepository

class ActionCoolDownRepositoryImpl(
    private val actionsCoolDown: ActionsCoolDown
): ActionCoolDownRepository {

    override fun saveActionCoolDown(action: Action) {
        actionsCoolDown.saveCoolDown(action)
    }
}
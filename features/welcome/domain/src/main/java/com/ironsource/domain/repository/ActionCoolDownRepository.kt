package com.ironsource.domain.repository

import com.ironsource.domain.model.Action

interface ActionCoolDownRepository {

    fun saveActionCoolDown(action: Action)
}
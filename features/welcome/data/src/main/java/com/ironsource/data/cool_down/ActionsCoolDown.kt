package com.ironsource.data.cool_down

import com.ironsource.domain.model.Action

interface ActionsCoolDown {

    fun saveCoolDown(action: Action)

    fun isActionAvailable(action: Action): Boolean
}
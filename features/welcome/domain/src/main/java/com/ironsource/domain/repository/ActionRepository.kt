package com.ironsource.domain.repository

import com.ironsource.domain.model.Action

interface ActionRepository {

    fun getActions(): List<Action>
}
package com.ironsource.domain.repository

import com.ironsource.domain.model.ActionsContainer

interface ActionRepository {

    fun getActions(): ActionsContainer
}
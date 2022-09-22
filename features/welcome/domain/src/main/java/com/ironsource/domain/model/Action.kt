package com.ironsource.domain.model

import java.io.Serializable

data class Action(
    val type: String,
    val enabled: Boolean,
    val priority: Int,
    val valid_days: List<Int>,
    val cool_down: Long
): Serializable
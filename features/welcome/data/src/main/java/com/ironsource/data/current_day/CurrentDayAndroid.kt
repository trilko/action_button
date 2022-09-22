package com.ironsource.data.current_day

import java.util.*

class CurrentDayAndroid: CurrentDay {

    override fun get(): Int {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    }
}
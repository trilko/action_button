package com.ironsource.data.cool_down

import android.content.SharedPreferences
import com.ironsource.domain.model.Action

class ActionsCoolDownPrefs(
    private val sharedPreferences: SharedPreferences
): ActionsCoolDown {

    override fun saveCoolDown(action: Action) {
        val expiredCoolDownTime = System.currentTimeMillis() + action.cool_down
        sharedPreferences.edit().putLong(action.type, expiredCoolDownTime).apply()
    }

    override fun isActionAvailable(action: Action): Boolean {
        return try {
            val expiredCoolDownTime = sharedPreferences.getLong(action.type, 0)
            val currentTime = System.currentTimeMillis()
            currentTime > expiredCoolDownTime
        } catch (e: Exception) {
            false
        }
    }

    private data class ActionWithCoolDown(
        val action: Action,
        val coolDown: Long
    ): java.io.Serializable
}
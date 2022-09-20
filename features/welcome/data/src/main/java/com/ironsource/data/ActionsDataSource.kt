package com.ironsource.data

import android.content.Context
import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ironsource.domain.model.Action
import com.ironsource.domain.repository.ActionRepository
import java.lang.reflect.Type

class ActionsDataSource(
    private val context: Context
) : ActionRepository {

    companion object {
        private const val JSON_FILE_NAME = "button_to_action_config.json"
    }

    override fun getActions(): List<Action> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Action>>() {}.type
        return gson.fromJson(
            context.assets.readAssetsFile(JSON_FILE_NAME), type
        )
    }

    private fun AssetManager.readAssetsFile(fileName: String): String {
        return open(fileName).bufferedReader().use { it.readText() }
    }
}
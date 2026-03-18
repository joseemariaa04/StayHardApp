package com.example.gymtracker.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsManager(private val context: Context) {
    companion object {
        val YELLOW_THRESHOLD = intPreferencesKey("yellow_threshold")
        val RED_THRESHOLD = intPreferencesKey("red_threshold")
    }

    val yellowThreshold: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[YELLOW_THRESHOLD] ?: 10
    }

    val redThreshold: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[RED_THRESHOLD] ?: 20
    }

    suspend fun updateThresholds(yellow: Int, red: Int) {
        context.dataStore.edit { preferences ->
            preferences[YELLOW_THRESHOLD] = yellow
            preferences[RED_THRESHOLD] = red
        }
    }
}

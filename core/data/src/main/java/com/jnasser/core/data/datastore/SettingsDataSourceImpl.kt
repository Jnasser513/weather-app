package com.jnasser.core.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.jnasser.core.domain.constants.PreferencesKeys
import com.jnasser.core.domain.repositories.SettingsRepository
import com.jnasser.core.domain.enums.TemperatureUnitsEnum
import com.jnasser.core.domain.enums.WindUnitsEnum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsDataSourceImpl(
    private val dataStore: DataStore<Preferences>
) : SettingsRepository {

    private val keyMap = mapOf(
        PreferencesKeys.KEY_WIND_UNITS to KEY_WIND_UNITS,
        PreferencesKeys.KEY_TEMPERATURE_UNITS to KEY_TEMPERATURE_UNITS
    )

    override val windUnits: Flow<WindUnitsEnum> = dataStore.data
        .map { prefs -> prefs.getEnum(KEY_WIND_UNITS, WindUnitsEnum.MILES) }

    override val temperatureUnits: Flow<TemperatureUnitsEnum> = dataStore.data
        .map { prefs -> prefs.getEnum(KEY_TEMPERATURE_UNITS, TemperatureUnitsEnum.STANDARD) }

    override suspend fun setValue(key: String, value: String) {
        val preferencesKey = keyMap[key]
            ?: throw IllegalArgumentException("Unknown preference key: $key")
        dataStore.edit { it[preferencesKey] = value }
    }

    private companion object {
        val KEY_WIND_UNITS = stringPreferencesKey("key_wind_units")
        val KEY_TEMPERATURE_UNITS = stringPreferencesKey("key_temperature_units")
    }
}

inline fun <reified T : Enum<T>> Preferences.getEnum(
    key: Preferences.Key<String>,
    default: T
): T {
    val name = this[key] ?: return default
    return runCatching { enumValueOf<T>(name) }.getOrDefault(default)
}

package com.jnasser.core.data.weather_detail.networking.common

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.jnasser.core.domain.common.SettingsRepository
import com.jnasser.core.domain.enums.WindUnitsEnum
import kotlinx.coroutines.flow.first

class SettingsDataSourceImpl(
    private val dataStore: DataStore<Preferences>
): SettingsRepository {

    override suspend fun getWindUnit(): WindUnitsEnum {
        val prefs = dataStore.data.first()
        val name = prefs[KEY_WIND_UNITS]
        return runCatching {
            WindUnitsEnum.valueOf(name.orEmpty())
        }.getOrDefault(WindUnitsEnum.MILES)
    }

    override suspend fun setWindUnit(unit: WindUnitsEnum) {
        dataStore.edit { it[KEY_WIND_UNITS] = unit.name }
    }

    private companion object {
        val KEY_WIND_UNITS = stringPreferencesKey("key_wind_units")
    }
}
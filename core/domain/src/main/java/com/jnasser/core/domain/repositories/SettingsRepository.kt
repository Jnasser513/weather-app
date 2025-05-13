package com.jnasser.core.domain.repositories

import com.jnasser.core.domain.enums.TemperatureUnitsEnum
import com.jnasser.core.domain.enums.WindUnitsEnum
import kotlinx.coroutines.flow.Flow
import java.util.prefs.Preferences

interface SettingsRepository {
    val windUnits: Flow<WindUnitsEnum>
    val temperatureUnits: Flow<TemperatureUnitsEnum>
    suspend fun setValue(key: String, value: String)
}
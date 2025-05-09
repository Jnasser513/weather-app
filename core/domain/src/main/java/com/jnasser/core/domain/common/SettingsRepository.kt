package com.jnasser.core.domain.common

import com.jnasser.core.domain.enums.TemperatureUnitsEnum
import com.jnasser.core.domain.enums.WindUnitsEnum
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val windUnits: Flow<WindUnitsEnum>
    val temperatureUnits: Flow<TemperatureUnitsEnum>
    suspend fun setWindUnit(unit: WindUnitsEnum)
    suspend fun setTemperatureUnits(unit: TemperatureUnitsEnum)
}
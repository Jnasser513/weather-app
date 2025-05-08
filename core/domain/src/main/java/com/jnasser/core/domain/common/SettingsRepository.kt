package com.jnasser.core.domain.common

import com.jnasser.core.domain.enums.WindUnitsEnum

interface SettingsRepository {
    suspend fun getWindUnit(): WindUnitsEnum
    suspend fun setWindUnit(unit: WindUnitsEnum)
}
package com.jnasser.core.domain.usecases

import com.jnasser.core.domain.coroutines.DispatcherProvider
import com.jnasser.core.domain.enums.WindUnitsEnum
import com.jnasser.core.domain.repositories.SettingsRepository
import kotlinx.coroutines.withContext

class UpdateWindUnitsUseCase(
    private val settingsRepository: SettingsRepository,
    private val dispatcher: DispatcherProvider
) {

    suspend fun updateWindUnits(unit: WindUnitsEnum) = withContext(dispatcher.io) {
        settingsRepository.s
    }
}
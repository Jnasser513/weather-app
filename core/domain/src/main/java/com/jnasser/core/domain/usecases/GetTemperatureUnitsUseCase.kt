package com.jnasser.core.domain.usecases

import com.jnasser.core.domain.repositories.SettingsRepository
import com.jnasser.core.domain.coroutines.DispatcherProvider
import com.jnasser.core.domain.enums.TemperatureUnitsEnum
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class GetTemperatureUnitsUseCase(
    private val settingsRepository: SettingsRepository,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend operator fun invoke() = withContext(dispatcherProvider.io) {
        runCatching {
            settingsRepository.temperatureUnits.first()
        }.getOrDefault(TemperatureUnitsEnum.STANDARD)
    }
}
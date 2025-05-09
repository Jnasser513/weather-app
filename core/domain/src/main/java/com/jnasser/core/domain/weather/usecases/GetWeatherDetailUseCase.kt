package com.jnasser.core.domain.weather.usecases

import com.jnasser.core.domain.common.SettingsRepository
import com.jnasser.core.domain.coroutines.DispatcherProvider
import com.jnasser.core.domain.enums.WindUnitsEnum
import com.jnasser.core.domain.weather.repositories.WeatherRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class GetWeatherDetailUseCase(
    private val weatherRepository: WeatherRepository,
    private val settingsRepository: SettingsRepository,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend operator fun invoke(lat: Double, lon: Double) =
        withContext(dispatcherProvider.io) {
            val windUnit = runCatching {
                settingsRepository.windUnits.first()
            }.getOrDefault(WindUnitsEnum.MILES)
            weatherRepository.getWeatherDetail(lat, lon, windUnit.name.lowercase())
        }
}
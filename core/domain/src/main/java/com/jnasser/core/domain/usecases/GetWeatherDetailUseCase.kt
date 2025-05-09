package com.jnasser.core.domain.usecases

import com.jnasser.core.domain.common.SettingsRepository
import com.jnasser.core.domain.coroutines.DispatcherProvider
import com.jnasser.core.domain.enums.TemperatureUnitsEnum
import com.jnasser.core.domain.weather.repositories.WeatherRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class GetWeatherDetailUseCase(
    private val weatherRepository: WeatherRepository,
    private val getTemperatureUnitsUseCase: GetTemperatureUnitsUseCase,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend operator fun invoke(lat: Double, lon: Double) =
        withContext(dispatcherProvider.io) {
            val temperatureUnits = getTemperatureUnitsUseCase().name.lowercase()
            weatherRepository.getWeatherDetail(lat, lon, temperatureUnits)
        }
}
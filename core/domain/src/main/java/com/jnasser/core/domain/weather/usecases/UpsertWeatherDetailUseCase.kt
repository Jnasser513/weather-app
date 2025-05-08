package com.jnasser.core.domain.weather.usecases

import com.jnasser.core.domain.coroutines.DispatcherProvider
import com.jnasser.core.domain.weather.repositories.WeatherRepository
import kotlinx.coroutines.withContext

class UpsertWeatherDetailUseCase(
    private val weatherRepository: WeatherRepository,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend operator fun invoke(lat: Double, lon: Double, units: String) =
        withContext(dispatcherProvider.io) {
            weatherRepository.upsertWeatherDetail(lat, lon, units)
        }
}
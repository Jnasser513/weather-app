package com.jnasser.core.domain.weather.usecases

import com.jnasser.core.domain.weather.repositories.WeatherRepository
import kotlinx.coroutines.withContext

class UpsertWeatherDetailUseCase(
    private val weatherRepository: WeatherRepository,
    private val
) {

    suspend operator fun invoke(lat: Double, lon: Double, units: String) = withContext()
        weatherRepository.upsertWeatherDetail(lat, lon, units)
}
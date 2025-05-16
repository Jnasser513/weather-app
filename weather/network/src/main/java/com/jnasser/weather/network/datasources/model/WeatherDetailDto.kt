package com.jnasser.weather.network.datasources.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDetailDto(
    val lat: Double,
    val lon: Double,
    val current: WeatherCurrentDetailDto,
    val hourly: List<WeatherHourlyDetailDto>,
    val daily: List<WeatherDailyDetailDto>
)

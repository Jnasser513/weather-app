package com.jnasser.weather.presentation.weather_detail.model

import com.jnasser.core.domain.constants.DefaultValues.EMPTY_STRING

data class WeatherDataUi(
    val isCurrent: Boolean = true,
    val day: String = EMPTY_STRING,
    val currentTemp: Float? = null,
    val maxTemp: Float? = null,
    val minTemp: Float? = null,
    val weatherDescription: String? = null,
    val temperatureFeelsLike: Float? = null,
    val windSpeed: Float? = null,
    val windDeg: Int? = null,
    val windData: WindDataUi = WindDataUi(),
    val uvDataUi: UVDataUi = UVDataUi()
)

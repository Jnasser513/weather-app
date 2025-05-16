package com.jnasser.weather.presentation.weather_detail.model

data class WeatherDataUi(
    val currentTemp: Float? = null,
    val weatherDescription: String? = null,
    val temperatureFeelsLike: Float? = null,
    val windSpeed: Float? = null,
    val windDeg: Int? = null,
    val windData: WindDataUi = WindDataUi(),
    val uvDataUi: UVDataUi = UVDataUi()
)

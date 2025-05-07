package com.jnasser.core.domain.weather_detail.model

data class WeatherCurrentDetail(
    val sunrise: Long,
    val sunset: Long,
    val temp: Float,
    val feelsLike: Float,
    val pressure: Int,
    val humidity: Int,
    val uvi: Float,
    val clouds: Int,
    val visibility: Int,
    val windSpeed: Float,
    val windDeg: Int,
    val weather: Weather,
    val rain: Rain,
    val snow: Snow
)

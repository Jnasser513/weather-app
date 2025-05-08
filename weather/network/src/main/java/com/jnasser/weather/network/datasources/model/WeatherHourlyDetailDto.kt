package com.jnasser.core.domain.weather.model

data class WeatherHourlyDetail(
    val dt: Long,
    val temp: Float,
    val feelsLike: Float,
    val pressure: Int,
    val humidity: Int,
    val uvi: Float,
    val clouds: Int,
    val visibility: Int,
    val windSpeed: Float,
    val windDeg: Int,
    val windGust: Float,
    val weather: Weather,
    val rain: Rain,
    val snow: Snow,
    val pop: Float
)

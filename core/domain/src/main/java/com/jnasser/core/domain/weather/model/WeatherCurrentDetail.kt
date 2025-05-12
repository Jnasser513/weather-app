package com.jnasser.core.domain.weather.model

data class WeatherCurrentDetail(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Float,
    val feelsLike: Float,
    val pressure: Int,
    val humidity: Int,
    val uvi: Float,
    val clouds: Int,
    val visibility: Int,
    val windDescription: String = "",
    val windSpeed: Float,
    val windDeg: Int,
    val windGust: Float? = null,
    val weather: List<Weather>,
    val rain: Rain? = null,
    val snow: Snow? = null
)

package com.jnasser.weather.network.datasources.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDailyDetailDto(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val moonrise: Long,
    val moonset: Long,
    @SerialName(value = "moon_phase") val moonPhase: Float,
    val summary: String,
    val temp: TemperatureDto,
    @SerialName(value = "feels_like") val feelsLike: TemperatureDto,
    val pressure: Int,
    val humidity: Int,
    val uvi: Float,
    val clouds: Int,
    @SerialName(value = "wind_speed") val windSpeed: Float,
    @SerialName(value = "wind_deg") val windDeg: Int,
    @SerialName(value = "wind_gust") val windGust: Float,
    val weather: List<WeatherDto>,
    val rain: Float? = null,
    val snow: Float? = null,
    val pop: Float,
)

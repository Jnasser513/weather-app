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
    val visibility: Int,
    @SerialName(value = "wind_speed") val windSpeed: Float,
    @SerialName(value = "wind_deg") val windDeg: Int,
    @SerialName(value = "wind_gust") val windGust: Float,
    val weather: WeatherDto,
    val rain: Float,
    val snow: Float,
    val pop: Int,
)

package com.jnasser.weather.network.datasources.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherHourlyDetailDto(
    val dt: Long,
    val temp: Float,
    @SerialName(value = "feels_like") val feelsLike: Float,
    val pressure: Int,
    val humidity: Int,
    val uvi: Float,
    val clouds: Int,
    val visibility: Int? = null,
    @SerialName(value = "wind_speed") val windSpeed: Float,
    @SerialName(value = "wind_deg") val windDeg: Int,
    @SerialName(value = "wind_gust") val windGust: Float,
    val weather: List<WeatherDto>,
    val rain: RainDto? = null,
    val snow: SnowDto? = null,
    val pop: Float
)

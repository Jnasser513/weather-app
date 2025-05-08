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
    val visibility: Int,
    @SerialName(value = "wind_spedd") val windSpeed: Float,
    @SerialName(value = "wind_deg") val windDeg: Int,
    @SerialName(value = "wind_gust") val windGust: Float,
    val weather: WeatherDto,
    val rain: RainDto,
    val snow: SnowDto,
    val pop: Float
)

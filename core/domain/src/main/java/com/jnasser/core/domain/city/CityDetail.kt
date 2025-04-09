package com.jnasser.core.domain.city

data class CityDetail(
    val sunrise: String,
    val sunset: String,
    val temperature: String,
    val temperatureFeels: String,
    val windSpeed: String,
    val windDirection: Float,
    val windGust: Float,
    val weather: Weather,
    val uvi: Float,
    val maxTemperature: String,
    val minTemperature: String,
    val preventUVHours: List<String>,
    val airQuality: Float,
    val airCO: Float,
    val precipitationProbability: String,
    val moonPhase: String,
    val stargazing: String
)

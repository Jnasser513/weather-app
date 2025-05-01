package com.jnasser.core.domain.city

import com.jnasser.core.domain.DefaultValues.EMPTY_DOUBLE
import com.jnasser.core.domain.DefaultValues.EMPTY_FLOAT
import com.jnasser.core.domain.DefaultValues.EMPTY_INT
import com.jnasser.core.domain.DefaultValues.EMPTY_STRING

data class CityDetail(
    val name: String = EMPTY_STRING,
    val sunrise: String = EMPTY_STRING,
    val sunset: String = EMPTY_STRING,
    val temperature: String = EMPTY_STRING,
    val temperatureFeels: String = EMPTY_STRING,
    val windSpeed: String = EMPTY_STRING,
    val windDirection: Float = EMPTY_FLOAT,
    val windGust: Float = EMPTY_FLOAT,
    val weather: Weather = Weather(),
    val uvi: Float = EMPTY_FLOAT,
    val maxTemperature: String = EMPTY_STRING,
    val minTemperature: String = EMPTY_STRING,
    val preventUVHours: List<String> = emptyList(),
    val airQuality: Int = EMPTY_INT,
    val airCO: Double = EMPTY_DOUBLE,
    val airO3: Double = EMPTY_DOUBLE,
    val no2: Double = EMPTY_DOUBLE,
    val precipitationProbability: String  = EMPTY_STRING,
    val moonPhase: String  = EMPTY_STRING,
    val stargazing: String = EMPTY_STRING
)

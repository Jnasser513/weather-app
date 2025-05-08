package com.jnasser.weather.network.datasources.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

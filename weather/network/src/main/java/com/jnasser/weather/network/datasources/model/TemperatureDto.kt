package com.jnasser.weather.network.datasources.model

import kotlinx.serialization.Serializable

@Serializable
data class TemperatureDto(
    val day: Float,
    val min: Float,
    val max: Float,
    val night: Float,
    val eve: Float,
    val morn: Float
)

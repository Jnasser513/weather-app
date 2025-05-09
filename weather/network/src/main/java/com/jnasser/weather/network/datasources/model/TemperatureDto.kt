package com.jnasser.weather.network.datasources.model

import kotlinx.serialization.Serializable

@Serializable
data class TemperatureDto(
    val day: Float,
    val min: Float? = null,
    val max: Float? = null,
    val night: Float,
    val eve: Float,
    val morn: Float
)

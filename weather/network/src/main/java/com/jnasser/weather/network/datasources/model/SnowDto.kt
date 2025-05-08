package com.jnasser.weather.network.datasources.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SnowDto(
    @SerialName(value = "1h") val value: Float
)

package com.jnasser.core.domain.weather.model

data class Temperature(
    val day: Float,
    val min: Float,
    val max: Float,
    val night: Float,
    val eve: Float,
    val morn: Float
)

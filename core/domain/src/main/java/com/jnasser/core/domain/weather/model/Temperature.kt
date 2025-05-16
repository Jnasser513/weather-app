package com.jnasser.core.domain.weather.model

data class Temperature(
    val day: Float,
    val min: Float? = null,
    val max: Float? = null,
    val night: Float,
    val eve: Float,
    val morn: Float
)

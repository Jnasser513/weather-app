package com.jnasser.core.domain.weather.model

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

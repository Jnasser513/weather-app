package com.jnasser.core.domain.weather_detail.model

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

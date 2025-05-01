package com.jnasser.weather.presentation.weather_detail.model

data class UVDataUi(
    val uvValue: Int,
    val state: String,
    val preventUVHours: List<String> = emptyList()
)

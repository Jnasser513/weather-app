package com.jnasser.weather.presentation.weather_detail.model

data class ForecastDataUi(
    val title: String,
    val icon: String,
    val maxTemperature: String,
    val minTemperature: String,
    val progress: Float
)

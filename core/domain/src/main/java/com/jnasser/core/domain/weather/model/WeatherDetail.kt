package com.jnasser.core.domain.weather.model


data class WeatherDetail(
    val lat: Double,
    val lon: Double,
    val current: WeatherCurrentDetail,
    val hourly: WeatherHourlyDetail,
    val daily: WeatherDailyDetail
)

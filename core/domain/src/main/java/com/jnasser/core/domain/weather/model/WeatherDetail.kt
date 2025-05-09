package com.jnasser.core.domain.weather.model

import com.jnasser.core.domain.DefaultValues.EMPTY_DOUBLE


data class WeatherDetail(
    val lat: Double = EMPTY_DOUBLE,
    val lon: Double = EMPTY_DOUBLE,
    val current: WeatherCurrentDetail? = null,
    val hourly: List<WeatherHourlyDetail>? = null,
    val daily: List<WeatherDailyDetail>? = null
)

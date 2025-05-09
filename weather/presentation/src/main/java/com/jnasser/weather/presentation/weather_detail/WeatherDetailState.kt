package com.jnasser.weather.presentation.weather_detail

import com.jnasser.core.domain.enums.WindUnitsEnum
import com.jnasser.core.domain.weather.model.WeatherDetail

data class WeatherDetailState(
    val isLoading: Boolean = false,
    val weather: WeatherDetail = WeatherDetail(),
    val windUnit: WindUnitsEnum = WindUnitsEnum.MILES
)
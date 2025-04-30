package com.jnasser.weather.presentation.weather_detail

import com.jnasser.core.domain.city.CityDetail
import com.jnasser.core.domain.enums.WindUnitsEnum

data class WeatherDetailState(
    val isLoading: Boolean = false,
    val city: CityDetail = CityDetail(),
    val windUnit: WindUnitsEnum = WindUnitsEnum.MILES
)
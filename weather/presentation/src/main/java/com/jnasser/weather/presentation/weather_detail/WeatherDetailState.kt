package com.jnasser.weather.presentation.weather_detail

import com.jnasser.core.domain.city.CityDetail

data class WeatherDetailState(
    val isLoading: Boolean = false,
    val city: CityDetail = CityDetail(),
)
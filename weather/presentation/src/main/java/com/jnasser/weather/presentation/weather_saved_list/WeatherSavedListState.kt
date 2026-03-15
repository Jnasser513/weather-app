package com.jnasser.weather.presentation.weather_saved_list

import com.jnasser.weather.presentation.weather_saved_list.model.CitySummary

data class WeatherSavedListState(
    val isLoading: Boolean = false,
    val cities: List<CitySummary> = emptyList()
)

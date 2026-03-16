package com.jnasser.weather.presentation.weather_saved_list

sealed interface WeatherSavedListAction {
    data class OnWeatherDetail(val id: Long): WeatherSavedListAction
}
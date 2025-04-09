package com.jnasser.weather.presentation.weather_detail

import com.jnasser.core.domain.city.CityDetail
import java.time.DayOfWeek

sealed interface WeatherDetailAction {
    data object OnGoHome: WeatherDetailAction
    data object OnChangeFilterToDaily: WeatherDetailAction
    data object OnChangeFilterToHourly: WeatherDetailAction
    data object OnMapDetail: WeatherDetailAction
    data class OnSelectDay(val day: DayOfWeek): WeatherDetailAction
    data class OnFollowUp(val city: CityDetail): WeatherDetailAction
    data class OnRemoveFollow(val id: Long): WeatherDetailAction
}
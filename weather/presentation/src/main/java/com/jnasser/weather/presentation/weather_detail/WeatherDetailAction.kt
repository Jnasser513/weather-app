package com.jnasser.weather.presentation.weather_detail

import com.jnasser.core.domain.city.CityDetail
import com.jnasser.core.domain.enums.WindUnitsEnum
import com.jnasser.weather.domain.repositories.ForecastSelection

sealed interface WeatherDetailAction {
    data object OnGoHome: WeatherDetailAction
    data class OnSelectToggle(val selection: ForecastSelection): WeatherDetailAction
    data object OnMapDetail: WeatherDetailAction
    data object OnGetTemperatureUnits: WeatherDetailAction
    data class OnGetWeatherDetail(val lat: Double, val lon: Double): WeatherDetailAction
    data class OnSelectForecast(val time: Long): WeatherDetailAction
    data class OnFollowUp(val city: CityDetail): WeatherDetailAction
    data class OnRemoveFollow(val id: Long): WeatherDetailAction
    data class OnChangeWindUnit(val unit: WindUnitsEnum): WeatherDetailAction
}
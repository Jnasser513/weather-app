package com.jnasser.weather.presentation.weather_detail

import com.jnasser.core.domain.enums.TemperatureUnitsEnum
import com.jnasser.core.domain.enums.WindUnitsEnum
import com.jnasser.core.domain.weather.model.WeatherDetail
import com.jnasser.weather.domain.repositories.ForecastSelection
import com.jnasser.weather.presentation.weather_detail.model.WeatherDataUi

data class WeatherDetailState(
    val isLoading: Boolean = false,
    val forecastSelection: ForecastSelection = ForecastSelection.DAILY,
    val weather: WeatherDetail = WeatherDetail(),
    val weatherSelection: WeatherDataUi = WeatherDataUi(),
    val temperatureUnits: TemperatureUnitsEnum = TemperatureUnitsEnum.STANDARD,
    val windUnit: WindUnitsEnum = WindUnitsEnum.MILES
)
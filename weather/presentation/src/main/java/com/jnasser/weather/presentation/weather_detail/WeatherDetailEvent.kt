package com.jnasser.weather.presentation.weather_detail

import com.jnasser.core.presentation.ui.UiText

sealed interface WeatherDetailEvent {
    data class Error(val error: UiText): WeatherDetailEvent
}
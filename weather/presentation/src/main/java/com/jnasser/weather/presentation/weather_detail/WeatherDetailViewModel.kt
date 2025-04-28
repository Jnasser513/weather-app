package com.jnasser.weather.presentation.weather_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class WeatherDetailViewModel: ViewModel() {

    var state by mutableStateOf(WeatherDetailState())
        private set

    fun onAction(action: WeatherDetailAction) {
        when(action) {
            WeatherDetailAction.OnChangeFilterToDaily -> TODO()
            WeatherDetailAction.OnChangeFilterToHourly -> TODO()
            is WeatherDetailAction.OnFollowUp -> TODO()
            is WeatherDetailAction.OnRemoveFollow -> TODO()
            is WeatherDetailAction.OnSelectDay -> TODO()
            else -> Unit
        }
    }
}
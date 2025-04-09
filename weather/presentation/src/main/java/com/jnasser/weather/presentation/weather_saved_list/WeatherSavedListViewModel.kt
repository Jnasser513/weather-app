package com.jnasser.weather.presentation.weather_saved_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class WeatherSavedListViewModel: ViewModel() {

    var state by mutableStateOf(WeatherSavedListState())
        private set
}
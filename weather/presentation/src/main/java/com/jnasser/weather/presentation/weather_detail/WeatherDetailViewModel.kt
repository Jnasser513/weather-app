package com.jnasser.weather.presentation.weather_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jnasser.core.domain.util.error_handler.DataError
import com.jnasser.core.domain.util.result_handler.Result
import com.jnasser.core.domain.weather.usecases.GetWeatherDetailUseCase
import com.jnasser.core.domain.weather.usecases.UpsertWeatherDetailUseCase
import com.jnasser.core.presentation.ui.utils.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class WeatherDetailViewModel(
    private val upsertWeatherDetailUseCase: UpsertWeatherDetailUseCase,
    private val getWeatherDetailUseCase: GetWeatherDetailUseCase
): ViewModel() {

    var state by mutableStateOf(WeatherDetailState())
        private set

    init {
        // TODO("Validate if city is in local db")
        onAction(WeatherDetailAction.OnGetWeatherDetail(13.700961, -89.209179))
    }

    private val eventChannel = Channel<WeatherDetailEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: WeatherDetailAction) {
        when(action) {
            WeatherDetailAction.OnChangeFilterToDaily -> TODO()
            WeatherDetailAction.OnChangeFilterToHourly -> TODO()
            is WeatherDetailAction.OnGetWeatherDetail -> getWeatherDetail(action.lat, action.lon)
            is WeatherDetailAction.OnFollowUp -> {}
            is WeatherDetailAction.OnRemoveFollow -> TODO()
            is WeatherDetailAction.OnSelectDay -> TODO()
            is WeatherDetailAction.OnChangeWindUnit -> state = state.copy(windUnit = action.unit)
            else -> Unit
        }
    }

    private fun getWeatherDetail(lat: Double, lon: Double) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = getWeatherDetailUseCase(lat, lon)
            state = state.copy(isLoading = false)

            when(result) {
                is Result.Error -> eventChannel.send(WeatherDetailEvent.Error(result.error.asUiText()))
                is Result.Success -> state = state.copy(weather = result.data)
            }
        }
    }
}
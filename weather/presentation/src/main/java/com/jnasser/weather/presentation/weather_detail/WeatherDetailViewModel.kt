package com.jnasser.weather.presentation.weather_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jnasser.core.domain.usecases.GetLocalCityByIdUseCase
import com.jnasser.core.domain.usecases.GetTemperatureUnitsUseCase
import com.jnasser.core.domain.util.result_handler.Result
import com.jnasser.core.domain.usecases.GetWeatherDetailUseCase
import com.jnasser.core.domain.usecases.GetWindUnitUseCase
import com.jnasser.core.domain.usecases.UpdateWindUnitsUseCase
import com.jnasser.core.domain.util.DateUtils
import com.jnasser.core.domain.util.withMinimumDuration
import com.jnasser.core.domain.weather.model.WeatherDetail
import com.jnasser.core.presentation.ui.utils.asUiText
import com.jnasser.weather.domain.repositories.ForecastSelection
import com.jnasser.weather.presentation.weather_detail.model.WeatherDataUi
import com.jnasser.weather.presentation.weather_detail.model.toWeatherDataUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class WeatherDetailViewModel(
    private val getWeatherDetailUseCase: GetWeatherDetailUseCase,
    private val getTemperatureUnitsUseCase: GetTemperatureUnitsUseCase,
    private val getWindUnitUseCase: GetWindUnitUseCase,
    private val updateWindUnitsUseCase: UpdateWindUnitsUseCase,
    private val getLocalCityByIdUseCase: GetLocalCityByIdUseCase
): ViewModel() {

    var state by mutableStateOf(WeatherDetailState())
        private set

    init {
        // TODO("Validate if city is in local db")
        onAction(WeatherDetailAction.OnGetTemperatureUnits)

        viewModelScope.launch {
            getWindUnitUseCase().collect { unit ->
                state = state.copy(windUnit = unit)
            }
        }
    }

    private val eventChannel = Channel<WeatherDetailEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: WeatherDetailAction) {
        when(action) {
            WeatherDetailAction.OnGetTemperatureUnits -> getTemperatureUnits()
            is WeatherDetailAction.OnSelectToggle -> state = state.copy(forecastSelection = action.selection)
            is WeatherDetailAction.OnGetWeatherDetail -> getWeatherDetail(action.lat, action.lon)
            is WeatherDetailAction.OnFollowUp -> {}
            is WeatherDetailAction.OnRemoveFollow -> TODO()
            is WeatherDetailAction.OnSelectForecast -> setForecastSelection(action.time)
            is WeatherDetailAction.OnChangeWindUnit -> viewModelScope.launch {
                updateWindUnitsUseCase(action.unit)
            }
            is WeatherDetailAction.OnGetCityDetail -> getCityDetail(action.cityId)
            else -> Unit
        }
    }

    private fun getCityDetail(cityId: String) = viewModelScope.launch {
        state = state.copy(isLoading = true)

        when (val result = getLocalCityByIdUseCase(cityId)) {
            is Result.Error ->
                eventChannel.send(WeatherDetailEvent.Error(result.error.asUiText()))

            is Result.Success ->
                onAction(WeatherDetailAction.OnGetWeatherDetail(result.data.lat, result.data.lon))
        }
    }

    private fun setForecastSelection(time: Long) {
        val forecastSelection = state.forecastSelection
        val isToday = DateUtils.isToday(time)
        if(isToday) {
            state = state.copy(
                weatherSelection = state.weather.current?.toWeatherDataUi() ?: WeatherDataUi()
            )
            return
        }
        val selectedItem =
            if(forecastSelection == ForecastSelection.DAILY) state.weather.daily?.find { it.dt == time }?.toWeatherDataUi()
            else state.weather.hourly?.find { it.dt == time }?.toWeatherDataUi()
        state = state.copy(weatherSelection = selectedItem ?: WeatherDataUi())
    }

    private fun getTemperatureUnits() {
        viewModelScope.launch {
            val temperatureUnits = getTemperatureUnitsUseCase()
            state = state.copy(temperatureUnits = temperatureUnits)
        }
    }

    private fun getWeatherDetail(lat: Double, lon: Double) {
        viewModelScope.launch {
            val result = withMinimumDuration {
                getWeatherDetailUseCase(lat, lon)
            }

            when (result) {
                is Result.Error ->
                    eventChannel.send(WeatherDetailEvent.Error(result.error.asUiText()))

                is Result.Success -> {
                    state = state.copy(
                        weather = result.data,
                        weatherSelection = result.data.current?.toWeatherDataUi() ?: WeatherDataUi()
                    )
                }
            }

            state = state.copy(isLoading = false)
        }
    }
}
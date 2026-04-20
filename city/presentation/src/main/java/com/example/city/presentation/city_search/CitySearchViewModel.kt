package com.example.city.presentation.city_search

import android.util.Log
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.city.presentation.city_search.mappers.toDomain
import com.example.city.presentation.city_search.mappers.toUiDomain
import com.example.city.presentation.city_search.model.CitySearchUI
import com.jnasser.core.domain.city.City
import com.jnasser.core.domain.usecases.GetPlaceLatLngUseCase
import com.jnasser.core.domain.usecases.GetPlacesSuggestionsUseCase
import com.jnasser.core.domain.usecases.UpsertCityUseCase
import com.jnasser.core.domain.util.result_handler.Result
import com.jnasser.core.presentation.ui.utils.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CitySearchViewModel(
    private val getPlacesSuggestionsUseCase: GetPlacesSuggestionsUseCase,
    private val getPlaceLatLngUseCase: GetPlaceLatLngUseCase,
    private val upsertCityUseCase: UpsertCityUseCase
): ViewModel() {

    var state by mutableStateOf(CitySearchViewState())
        private  set

    private val eventChannel = Channel<CitySearchEvents>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: CitySearchAction) {
        when(action) {
            CitySearchAction.OnCancel -> viewModelScope.launch { eventChannel.send(CitySearchEvents.OnReturn) }
            is CitySearchAction.OnSearch -> searchCity(action.query)
            is CitySearchAction.OnSaveCity -> getPlaceLatLng(action.city)
            else -> Unit
        }
    }

    private fun searchCity(query: String) {
        if(query.length < 3) {
            state = state.copy(suggestions = emptyList())
            return
        }

        // TODO("Manejar esto con un flow de manera mas reactiva")
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = getPlacesSuggestionsUseCase.invoke(query)

            state = state.copy(isLoading = false)
            when(result) {
                is Result.Error -> eventChannel.send(CitySearchEvents.Error(result.error.asUiText()))
                is Result.Success -> state = state.copy(suggestions = result.data.toUiDomain())
            }
        }
    }

    private fun getPlaceLatLng(city: CitySearchUI) = viewModelScope.launch {
        val result = getPlaceLatLngUseCase(city.toDomain())

        when(result) {
            is Result.Error -> eventChannel.send(CitySearchEvents.Error(result.error.asUiText()))
            is Result.Success -> saveCity(result.data)
        }
    }

    private fun saveCity(city: City) = viewModelScope.launch {
        val result = upsertCityUseCase(city)

        when(result) {
            is Result.Error -> eventChannel.send(CitySearchEvents.Error(result.error.asUiText()))
            is Result.Success -> {
                Log.d("CitySearchViewModel", "City saved successfully")
            }
        }
    }

}
package com.example.city.presentation.city_search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.city.presentation.city_search.mappers.toCitySearchUIList
import com.jnasser.core.domain.usecases.GetPlacesSuggestionsUseCase
import com.jnasser.core.domain.util.result_handler.Result
import com.jnasser.core.presentation.ui.utils.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.acos

class CitySearchViewModel(
    private val getPlacesSuggestionsUseCase: GetPlacesSuggestionsUseCase
): ViewModel() {

    var state by mutableStateOf(CitySearchViewState())
        private  set

    private val eventChannel = Channel<CitySearchEvents>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: CitySearchAction) {
        when(action) {
            CitySearchAction.OnCancel -> viewModelScope.launch { eventChannel.send(CitySearchEvents.OnReturn) }
            is CitySearchAction.OnSearch -> searchCity(action.query)
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
                is Result.Success -> state = state.copy(suggestions = result.data.toCitySearchUIList())
            }
        }
    }

}
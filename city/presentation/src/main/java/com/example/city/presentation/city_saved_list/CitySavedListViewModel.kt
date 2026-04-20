package com.example.city.presentation.city_saved_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.city.presentation.city_saved_list.di.cityPresentationModule
import com.example.city.presentation.city_saved_list.mappers.toUiDomain
import com.jnasser.core.domain.repositories.CityRepository
import com.jnasser.core.domain.usecases.GetLocalCitiesUseCase
import com.jnasser.core.domain.usecases.GetPlacesSuggestionsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CitySavedListViewModel(
    private val getLocalCitiesUseCase: GetLocalCitiesUseCase
): ViewModel() {

    var state by mutableStateOf(CitySavedListState())
        private set

    init {
        getLocalCitiesUseCase().onEach { city ->
            val cityUi = city.map { it.toUiDomain() }
            state = state.copy(cities = cityUi)
        }.launchIn(viewModelScope)
    }

}
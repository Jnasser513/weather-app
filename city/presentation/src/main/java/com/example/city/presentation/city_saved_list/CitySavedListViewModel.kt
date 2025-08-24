package com.example.city.presentation.city_saved_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jnasser.core.domain.usecases.GetPlacesSuggestionsUseCase

class CitySavedListViewModel(

): ViewModel() {

    var state by mutableStateOf(CitySavedListState())
        private set
}
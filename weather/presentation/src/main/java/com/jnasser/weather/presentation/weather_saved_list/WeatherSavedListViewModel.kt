package com.jnasser.weather.presentation.weather_saved_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jnasser.core.domain.usecases.GetPlacesSuggestionsUseCase
import com.jnasser.core.domain.util.result_handler.Result
import com.jnasser.core.presentation.ui.utils.asUiText
import kotlinx.coroutines.launch
import timber.log.Timber

class WeatherSavedListViewModel(
    private val getPlacesSuggestionsUseCase: GetPlacesSuggestionsUseCase
): ViewModel() {

    var state by mutableStateOf(WeatherSavedListState())
        private set
}
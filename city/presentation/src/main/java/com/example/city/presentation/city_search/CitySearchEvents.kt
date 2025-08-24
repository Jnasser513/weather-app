package com.example.city.presentation.city_search

import com.jnasser.core.presentation.ui.UiText

sealed interface CitySearchEvents {
    data class Error(val error: UiText): CitySearchEvents
    data object OnReturn: CitySearchEvents
}
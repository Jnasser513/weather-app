package com.example.city.presentation.city_search

import com.example.city.presentation.city_search.model.CitySearchUI

data class CitySearchViewState(
    val isLoading: Boolean = false,
    val suggestions: List<CitySearchUI> = emptyList(),
    val searchQuery: String = ""
)

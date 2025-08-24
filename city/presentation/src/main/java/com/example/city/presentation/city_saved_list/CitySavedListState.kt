package com.example.city.presentation.city_saved_list

import com.example.city.presentation.city_saved_list.model.CitySummary

data class CitySavedListState(
    val isLoading: Boolean = false,
    val cities: List<CitySummary> = emptyList()
)

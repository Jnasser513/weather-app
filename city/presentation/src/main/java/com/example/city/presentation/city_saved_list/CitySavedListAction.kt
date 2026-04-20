package com.example.city.presentation.city_saved_list

sealed interface CitySavedListAction {
    data class OnCityDetail(val lat: Double, val lon: Double): CitySavedListAction
    data object OnSearchCity: CitySavedListAction
}
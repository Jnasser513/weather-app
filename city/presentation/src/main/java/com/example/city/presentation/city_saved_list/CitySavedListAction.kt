package com.example.city.presentation.city_saved_list

sealed interface CitySavedListAction {
    data class OnCityDetail(val cityId: String): CitySavedListAction
    data object OnSearchCity: CitySavedListAction
}

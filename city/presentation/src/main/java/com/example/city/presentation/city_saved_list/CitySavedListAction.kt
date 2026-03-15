package com.example.city.presentation.city_saved_list

sealed interface CitySavedListAction {
    data class OnCityDetail(val id: Long): CitySavedListAction
    data object OnSearchCity: CitySavedListAction
}
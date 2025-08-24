package com.example.city.presentation.city_search

sealed interface CitySearchAction {
    data class OnSearch(val query: String): CitySearchAction
    data object OnCancel: CitySearchAction
    //data class OnSaveCity(val city: CityDetail)
    data class OnCityDetails(val lat: Double, val lon: Double)
}
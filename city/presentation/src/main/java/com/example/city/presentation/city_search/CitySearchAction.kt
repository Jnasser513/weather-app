package com.example.city.presentation.city_search

import com.example.city.presentation.city_search.model.CitySearchUI

sealed interface CitySearchAction {
    data class OnSearch(val query: String): CitySearchAction
    data object OnCancel: CitySearchAction
    data class OnSaveCity(val city: CitySearchUI): CitySearchAction
    data class OnCityDetail(val lat: Double, val lon: Double): CitySearchAction
}
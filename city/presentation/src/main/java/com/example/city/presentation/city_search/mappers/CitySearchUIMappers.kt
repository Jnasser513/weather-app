package com.example.city.presentation.city_search.mappers

import com.example.city.presentation.city_search.model.CitySearchUI
import com.jnasser.core.domain.city.Place

fun List<Place>.toCitySearchUIList() = map { it.toCitySearchUI() }

fun Place.toCitySearchUI() = CitySearchUI(
    id = id,
    primaryText = primaryText,
    secondaryText = secondaryText
)
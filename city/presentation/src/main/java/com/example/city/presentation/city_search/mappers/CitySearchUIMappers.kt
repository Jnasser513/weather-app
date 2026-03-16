package com.example.city.presentation.city_search.mappers

import com.example.city.presentation.city_search.model.CitySearchUI
import com.jnasser.core.domain.city.City

fun List<City>.toUiDomain() = map { it.toUiDomain() }

fun City.toUiDomain() = CitySearchUI(
    id = id,
    place = place
)
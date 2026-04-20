package com.example.city.presentation.city_saved_list.mappers

import com.example.city.presentation.city_saved_list.model.CitySummary
import com.jnasser.core.domain.city.City

fun City.toUiDomain() = CitySummary(
    id = id,
    name = place,
    temperature = "10.8",
    condition = "Too Cold",
    lat = lat,
    lon = lon
)
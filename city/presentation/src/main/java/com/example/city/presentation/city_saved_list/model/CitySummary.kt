package com.example.city.presentation.city_saved_list.model


data class CitySummary(
    val id: String,
    val name: String,
    val temperature: String,
    val condition: String,
    val lat: Double,
    val lon: Double
)

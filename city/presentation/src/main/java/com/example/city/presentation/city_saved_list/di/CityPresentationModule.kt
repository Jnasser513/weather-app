package com.example.city.presentation.city_saved_list.di

import com.example.city.presentation.city_saved_list.CitySavedListViewModel
import com.example.city.presentation.city_search.CitySearchViewModel
import com.jnasser.core.domain.usecases.GetPlacesSuggestionsUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val cityPresentationModule = module {
    viewModelOf(::CitySavedListViewModel)
    viewModelOf(::CitySearchViewModel)

    // Use Cases
    singleOf(::GetPlacesSuggestionsUseCase)
}
package com.jnasser.weather.presentation.di

import com.jnasser.core.domain.usecases.ConvertWindSpeedUseCase
import com.jnasser.core.domain.usecases.GetPlacesSuggestionsUseCase
import com.jnasser.core.domain.usecases.GetTemperatureUnitsUseCase
import com.jnasser.core.domain.usecases.GetWeatherDetailUseCase
import com.jnasser.core.domain.usecases.GetWindUnitUseCase
import com.jnasser.core.domain.usecases.UpdateWindUnitsUseCase
import com.jnasser.core.domain.usecases.UpsertWeatherDetailUseCase
import com.jnasser.weather.presentation.weather_detail.WeatherDetailViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

var weatherPresentationModule = module {
    viewModelOf(::WeatherDetailViewModel)

    // Use cases
    singleOf(::UpsertWeatherDetailUseCase)
    singleOf(::GetWeatherDetailUseCase)
    singleOf(::GetTemperatureUnitsUseCase)
    singleOf(::UpdateWindUnitsUseCase)
    singleOf(::GetWindUnitUseCase)
    singleOf(::ConvertWindSpeedUseCase)
}
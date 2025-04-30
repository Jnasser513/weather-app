package com.jnasser.weather.presentation.di

import com.jnasser.weather.presentation.weather_detail.WeatherDetailViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

var weatherModule = module {
    viewModelOf(::WeatherDetailViewModel)
}
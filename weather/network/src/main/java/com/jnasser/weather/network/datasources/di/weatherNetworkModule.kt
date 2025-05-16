package com.jnasser.weather.network.datasources.di

import com.jnasser.core.domain.weather.datasources.RemoteWeatherDetailDataSource
import com.jnasser.weather.network.datasources.KtorWeatherDetailDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val weatherNetworkModule = module {
    singleOf(::KtorWeatherDetailDataSource).bind<RemoteWeatherDetailDataSource>()
}
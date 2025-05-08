package com.jnasser.core.data.weather_detail.networking.di

import com.jnasser.core.data.weather_detail.networking.coroutines.DispatcherProviderImpl
import com.jnasser.core.domain.coroutines.DispatcherProvider
import org.koin.dsl.module

val coreDataModule = module {
    single<DispatcherProvider> { DispatcherProviderImpl() }g
}
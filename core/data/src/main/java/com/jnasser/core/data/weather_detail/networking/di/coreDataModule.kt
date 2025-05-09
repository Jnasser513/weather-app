package com.jnasser.core.data.weather_detail.networking.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.jnasser.core.data.BuildConfig
import com.jnasser.core.data.weather_detail.networking.HttpClientFactory
import com.jnasser.core.data.weather_detail.networking.repositories.SettingsDataSourceImpl
import com.jnasser.core.data.weather_detail.networking.coroutines.DispatcherProviderImpl
import com.jnasser.core.data.weather_detail.networking.weather.repositories.OfflineFirstWeatherRepository
import com.jnasser.core.domain.repositories.SettingsRepository
import com.jnasser.core.domain.coroutines.DispatcherProvider
import com.jnasser.core.domain.weather.repositories.WeatherRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import java.io.File

private const val SHARED_DATA_STORE_PREFERENCE_NAME = "datastore.weatherapp.settings"

val coreDataModule = module {
    // Network
    single {
        HttpClientFactory().build()
    }

    // Coroutines
    singleOf(::DispatcherProviderImpl).bind<DispatcherProvider>()

    // DataStore
    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.create(
            produceFile = { File(androidContext().filesDir, SHARED_DATA_STORE_PREFERENCE_NAME) }
        )
    }
    singleOf(::SettingsDataSourceImpl).bind<SettingsRepository>()

    // Repositories
    singleOf(::OfflineFirstWeatherRepository).bind<WeatherRepository>()

    // Keys
    single<String> {
        BuildConfig.API_KEY
    }
}
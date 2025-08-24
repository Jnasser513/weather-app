package com.jnasser.core.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.google.android.libraries.places.api.Places
import com.jnasser.core.data.BuildConfig
import com.jnasser.core.data.datastore.GCPPlacesDataSource
import com.jnasser.core.data.weather_detail.networking.HttpClientFactory
import com.jnasser.core.data.datastore.SettingsDataSourceImpl
import com.jnasser.core.data.repositories.OfflineFirstCityRepository
import com.jnasser.core.data.weather_detail.networking.coroutines.DispatcherProviderImpl
import com.jnasser.core.data.weather_detail.networking.weather.repositories.OfflineFirstWeatherRepository
import com.jnasser.core.domain.city.datasources.RemotePlacesDataSource
import com.jnasser.core.domain.repositories.SettingsRepository
import com.jnasser.core.domain.coroutines.DispatcherProvider
import com.jnasser.core.domain.repositories.CityRepository
import com.jnasser.core.domain.weather.repositories.WeatherRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import timber.log.Timber
import java.io.File

private const val SHARED_DATA_STORE_PREFERENCE_NAME = "weatherapp.settings.preferences_pb"

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
    singleOf(::OfflineFirstCityRepository).bind<CityRepository>()

    // DataSources
    singleOf(::GCPPlacesDataSource).bind<RemotePlacesDataSource>()

    // Keys
    single<String> {
        BuildConfig.API_KEY
    }

    // Places SDK initialization
    single {
        val apiKey = "AIzaSyDSy1FxhndrTEsAxi1UVFy03tWAbfi7MJU"
        if(apiKey.isEmpty() || apiKey == "DEFAULT_API_KEY")
            Timber.tag("Places API").e("No api key")

        Places.initializeWithNewPlacesApiEnabled(androidContext(), apiKey)
        Places.createClient(androidContext())
    }
}
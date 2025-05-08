package com.jnasser.core.data.weather_detail.networking.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.jnasser.core.data.weather_detail.networking.common.SettingsDataSourceImpl
import com.jnasser.core.data.weather_detail.networking.coroutines.DispatcherProviderImpl
import com.jnasser.core.domain.common.SettingsRepository
import com.jnasser.core.domain.coroutines.DispatcherProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.io.File

private const val SHARED_DATA_STORE_PREFERENCE_NAME = "datastore.weatherapp.settings"

val coreDataModule = module {
    single<DispatcherProvider> { DispatcherProviderImpl() }

    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.create(
            produceFile = { File(androidContext().filesDir, SHARED_DATA_STORE_PREFERENCE_NAME) }
        )
    }

    single<SettingsRepository> { SettingsDataSourceImpl(get()) }
}
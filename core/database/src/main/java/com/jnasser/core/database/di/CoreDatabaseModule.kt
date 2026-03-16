package com.jnasser.core.database.di

import androidx.room.Room
import com.jnasser.core.database.WeatherAppDatabase
import com.jnasser.core.database.WeatherAppDatabaseConstants.WEATHER_APP_DB_NAME
import com.jnasser.core.database.datasources.RoomLocalCityDataSource
import com.jnasser.core.domain.city.datasources.LocalCityDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDatabaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            WeatherAppDatabase::class.java,
            WEATHER_APP_DB_NAME
        ).build()
    }

    single { get<WeatherAppDatabase>().cityDao }

    singleOf(::RoomLocalCityDataSource).bind<LocalCityDataSource>()
}
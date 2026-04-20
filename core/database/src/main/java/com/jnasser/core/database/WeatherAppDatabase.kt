package com.jnasser.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jnasser.core.database.WeatherAppDatabaseConstants.WEATHER_APP_DB_VERSION
import com.jnasser.core.database.dao.CityDao
import com.jnasser.core.database.entity.CityEntity

@Database(
    entities = [
        CityEntity::class
    ],
    version = WEATHER_APP_DB_VERSION
)
abstract class WeatherAppDatabase: RoomDatabase() {
    abstract val cityDao: CityDao
}
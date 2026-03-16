package com.jnasser.core.database.datasources

import android.database.sqlite.SQLiteFullException
import com.jnasser.core.database.dao.CityDao
import com.jnasser.core.database.mappers.toDomain
import com.jnasser.core.database.mappers.toEntity
import com.jnasser.core.domain.city.City
import com.jnasser.core.domain.city.datasources.CityId
import com.jnasser.core.domain.city.datasources.LocalCityDataSource
import com.jnasser.core.domain.util.error_handler.DataError
import com.jnasser.core.domain.util.result_handler.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomLocalCityDataSource(
    private val cityDao: CityDao
): LocalCityDataSource {

    override suspend fun getCities(): Flow<List<City>> {
        return cityDao.getAll()
            .map { cityEntity ->
                cityEntity.map { it.toDomain() }
            }
    }

    override suspend fun upsertCity(city: City): Result<CityId, DataError.Local> {
        return try {
            val entity = city.toEntity()
            val id = cityDao.upsert(entity)
            Result.Success(id.toString())
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }
}
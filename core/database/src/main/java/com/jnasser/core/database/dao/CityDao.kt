package com.jnasser.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.jnasser.core.database.entity.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {
    @Upsert
    suspend fun upsert(city: CityEntity)

    @Query("SELECT * FROM city_entity")
    fun getAll(): Flow<List<CityEntity>>

    @Query("SELECT * FROM city_entity WHERE id = :id")
    suspend fun getById(id: String): CityEntity?
}
package com.jnasser.core.domain.repositories

import com.jnasser.core.domain.city.City
import com.jnasser.core.domain.city.datasources.CityId
import com.jnasser.core.domain.util.error_handler.DataError
import com.jnasser.core.domain.util.result_handler.Result
import kotlinx.coroutines.flow.Flow

interface CityRepository {
    suspend fun findAutocompletePredictions(query: String): Result<List<City>, DataError.Network>
    suspend fun getPlaceLatLng(city: City): Result<City, DataError.Network>

    suspend fun getCities(): Flow<List<City>>
    suspend fun upsertCity(city: City): Result<CityId, DataError.Local>
}
package com.jnasser.core.domain.city.datasources

import com.jnasser.core.domain.city.City
import com.jnasser.core.domain.util.error_handler.DataError
import com.jnasser.core.domain.util.result_handler.Result
import kotlinx.coroutines.flow.Flow

typealias CityId = String

interface LocalCityDataSource {

    suspend fun getCities(): Flow<List<City>>

    suspend fun upsertCity(city: City): Result<CityId, DataError.Local>
}
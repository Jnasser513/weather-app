package com.jnasser.core.domain.city.datasources

import com.jnasser.core.domain.city.City
import com.jnasser.core.domain.city.CityDetail
import com.jnasser.core.domain.util.error_handler.DataError
import com.jnasser.core.domain.util.result_handler.Result
import kotlinx.coroutines.flow.Flow

typealias CityId = String

interface LocalCityDataSource {

    fun getCities(): Flow<List<City>>

    suspend fun getCityById(cityId: CityId): Result<City, DataError.Local>

    suspend fun upsertCity(city: City): Result<CityId, DataError.Local>
}
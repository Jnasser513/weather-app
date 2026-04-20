package com.jnasser.core.data.repositories

import com.jnasser.core.data.datastore.GCPPlacesDataSource
import com.jnasser.core.domain.city.City
import com.jnasser.core.domain.city.datasources.CityId
import com.jnasser.core.domain.city.datasources.LocalCityDataSource
import com.jnasser.core.domain.repositories.CityRepository
import com.jnasser.core.domain.util.error_handler.DataError
import com.jnasser.core.domain.util.result_handler.Result
import kotlinx.coroutines.flow.Flow

class OfflineFirstCityRepository(
    private val gcpPlacesDataSource: GCPPlacesDataSource,
    private val localCityDataSource: LocalCityDataSource
): CityRepository {

    override suspend fun findAutocompletePredictions(query: String): Result<List<City>, DataError.Network> =
        gcpPlacesDataSource.findAutocompletePrediction(query)

    override suspend fun getPlaceLatLng(city: City): Result<City, DataError.Network> =
        gcpPlacesDataSource.getPlaceLatLng(city)

    override fun getCities(): Flow<List<City>> = localCityDataSource.getCities()

    override suspend fun getCityById(cityId: String): Result<City, DataError.Local> =
        localCityDataSource.getCityById(cityId)

    override suspend fun upsertCity(city: City): Result<CityId, DataError.Local> =
        localCityDataSource.upsertCity(city)
}
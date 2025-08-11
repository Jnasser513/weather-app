package com.jnasser.core.data.repositories

import com.jnasser.core.data.datastore.GCPPlacesDataSource
import com.jnasser.core.domain.city.Place
import com.jnasser.core.domain.repositories.CityRepository
import com.jnasser.core.domain.util.error_handler.DataError
import com.jnasser.core.domain.util.result_handler.Result

class OfflineFirstCityRepository(
    private val gcpPlacesDataSource: GCPPlacesDataSource
): CityRepository {

    override suspend fun findAutocompletePredictions(query: String): Result<List<Place>, DataError.Network> =
        gcpPlacesDataSource.findAutocompletePrediction(query)
}
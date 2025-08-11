package com.jnasser.core.domain.repositories

import com.jnasser.core.domain.city.Place
import com.jnasser.core.domain.util.error_handler.DataError
import com.jnasser.core.domain.util.result_handler.Result

interface CityRepository {
    suspend fun findAutocompletePredictions(query: String): Result<List<Place>, DataError.Network>
}
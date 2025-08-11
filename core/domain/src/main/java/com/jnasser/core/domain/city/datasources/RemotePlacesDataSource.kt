package com.jnasser.core.domain.city.datasources

import com.jnasser.core.domain.city.Place
import com.jnasser.core.domain.util.error_handler.DataError
import com.jnasser.core.domain.util.result_handler.Result

interface RemotePlacesDataSource {
    suspend fun findAutocompletePrediction(query: String): Result<List<Place>, DataError.Network>
}
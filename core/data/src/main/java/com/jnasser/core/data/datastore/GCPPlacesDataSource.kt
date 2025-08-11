package com.jnasser.core.data.datastore

import android.text.style.CharacterStyle
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.jnasser.core.domain.city.Place
import com.jnasser.core.domain.city.datasources.RemotePlacesDataSource
import com.jnasser.core.domain.util.error_handler.DataError
import com.jnasser.core.domain.util.result_handler.Result
import io.ktor.utils.io.printStack
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class GCPPlacesDataSource(
    private val client: PlacesClient
): RemotePlacesDataSource {

    override suspend fun findAutocompletePrediction(query: String): Result<List<Place>, DataError.Network> {
        val request = FindAutocompletePredictionsRequest.builder()
            .setQuery(query)
            .setTypesFilter(
                listOf(
                    "locality",
                    "administrative_area_level_1",
                    "administrative_area_level_2",
                    "country"
                )
            )
            .build()

        return try {
            val response = client.findAutocompletePredictions(request).await()
            val places = response.autocompletePredictions.map { prediction ->
                Place(
                    id = prediction.placeId,
                    primaryText = prediction.getPrimaryText(null).toString(),
                    secondaryText = prediction.getSecondaryText(null).toString()
                )
            }

            Result.Success(places)
        } catch (e: Exception) {
            e.printStack()
            Timber.tag(TAG).e(e.localizedMessage)
            Result.Error(DataError.Network.SERIALIZATION)
        }
    }

    companion object {
        private const val TAG = "GCPPlacesDataSource"
    }
}
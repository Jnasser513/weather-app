package com.jnasser.core.data.datastore

import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.jnasser.core.domain.city.City
import com.jnasser.core.domain.city.datasources.RemotePlacesDataSource
import com.jnasser.core.domain.util.error_handler.DataError
import com.jnasser.core.domain.util.result_handler.Result
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class GCPPlacesDataSource(
    private val client: PlacesClient
): RemotePlacesDataSource {

    override suspend fun findAutocompletePrediction(query: String): Result<List<City>, DataError.Network> {
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
                City(
                    id = prediction.placeId,
                    place = "${prediction.getPrimaryText(null)}, ${prediction.getSecondaryText(null)}",
                    lat = 0.0,
                    lon = 0.0
                )
            }

            Result.Success(places)
        } catch (e: Exception) {
            Timber.tag(TAG).e(e.localizedMessage)
            Result.Error(DataError.Network.SERIALIZATION)
        }
    }

    override suspend fun getPlaceLatLng(city: City): Result<City, DataError.Network> {
        val placeFields = listOf(Place.Field.LOCATION)

        return try {
            val request = FetchPlaceRequest.builder(city.id, placeFields).build()
            val response = client.fetchPlace(request).await()

            response.place.location?.let {
                Result.Success(
                    city.copy(
                        lat = it.latitude,
                        lon = it.longitude
                    )
                )
            } ?: run {
                Result.Error(DataError.Network.NOT_FOUND)
            }
        } catch (e: Exception) {
            Timber.tag(TAG).e(e.localizedMessage)
            Result.Error(DataError.Network.SERIALIZATION)
        }
    }

    companion object {
        private const val TAG = "GCPPlacesDataSource"
    }
}
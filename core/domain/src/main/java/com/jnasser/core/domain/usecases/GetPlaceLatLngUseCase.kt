package com.jnasser.core.domain.usecases

import com.jnasser.core.domain.city.City
import com.jnasser.core.domain.coroutines.DispatcherProvider
import com.jnasser.core.domain.repositories.CityRepository
import kotlinx.coroutines.withContext

class GetPlaceLatLngUseCase(
    private val repository: CityRepository,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend operator fun invoke(city: City) = withContext(dispatcherProvider.io) {
        repository.getPlaceLatLng(city)
    }
}
package com.jnasser.core.domain.usecases

import com.jnasser.core.domain.city.City
import com.jnasser.core.domain.city.datasources.LocalCityDataSource
import com.jnasser.core.domain.coroutines.DispatcherProvider
import com.jnasser.core.domain.repositories.CityRepository
import kotlinx.coroutines.withContext

class UpsertCityUseCase(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: CityRepository
) {

    suspend operator fun invoke(city: City) = withContext(dispatcherProvider.io) {
        repository.upsertCity(city)
    }
}
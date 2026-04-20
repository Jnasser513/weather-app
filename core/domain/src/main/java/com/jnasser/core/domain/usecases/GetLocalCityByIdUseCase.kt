package com.jnasser.core.domain.usecases

import com.jnasser.core.domain.coroutines.DispatcherProvider
import com.jnasser.core.domain.repositories.CityRepository
import kotlinx.coroutines.withContext

class GetLocalCityByIdUseCase(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: CityRepository
) {

    suspend operator fun invoke(cityId: String) = withContext(dispatcherProvider.io) {
        repository.getCityById(cityId)
    }
}
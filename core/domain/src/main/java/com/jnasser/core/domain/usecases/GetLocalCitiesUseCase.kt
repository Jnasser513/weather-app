package com.jnasser.core.domain.usecases

import com.jnasser.core.domain.coroutines.DispatcherProvider
import com.jnasser.core.domain.repositories.CityRepository
import kotlinx.coroutines.withContext

class GetLocalCitiesUseCase(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: CityRepository
) {

    operator fun invoke() = repository.getCities()
}
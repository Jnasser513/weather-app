package com.jnasser.core.domain.usecases

import com.jnasser.core.domain.coroutines.DispatcherProvider
import com.jnasser.core.domain.repositories.CityRepository
import kotlinx.coroutines.withContext

class GetPlacesSuggestionsUseCase(
    private val repository: CityRepository,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend operator fun invoke(query: String) = withContext(dispatcherProvider.io) {
        repository.findAutocompletePredictions(query)
    }
}
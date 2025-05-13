package com.jnasser.core.domain.usecases

import com.jnasser.core.domain.repositories.SettingsRepository

class GetWindUnitUseCase(
    private val settingsRepository: SettingsRepository
) {

    suspend operator fun invoke() = settingsRepository.windUnits
}
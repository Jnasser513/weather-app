package com.jnasser.core.domain.usecases

import com.jnasser.core.domain.enums.WindUnitsEnum
import com.jnasser.core.domain.enums.WindUnitsEnum.KILOMETERS
import com.jnasser.core.domain.enums.WindUnitsEnum.METERS
import com.jnasser.core.domain.enums.WindUnitsEnum.MILES

class ConvertWindSpeedUseCase {

    operator fun invoke(value: Double, from: WindUnitsEnum, type: WindUnitsEnum): Double {
        val valueInMps = when(from) {
            MILES -> value * 0.44704
            KILOMETERS -> value * 0.277778
            METERS -> value
        }

        return when(type) {
            MILES -> valueInMps / 0.44704
            KILOMETERS -> valueInMps / 0.2277778
            METERS -> value
        }
    }
}
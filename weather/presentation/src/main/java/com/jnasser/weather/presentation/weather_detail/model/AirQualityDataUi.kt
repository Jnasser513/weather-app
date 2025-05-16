package com.jnasser.weather.presentation.weather_detail.model

import com.jnasser.core.domain.constants.DefaultValues.EMPTY_DOUBLE
import com.jnasser.core.domain.constants.DefaultValues.EMPTY_INT

data class AirQualityDataUi(
    val airQuality: Int = EMPTY_INT,
    val airCo: Double = EMPTY_DOUBLE,
    val airNO2: Double = EMPTY_DOUBLE,
    val o3: Double = EMPTY_DOUBLE
)

package com.jnasser.weather.presentation.weather_detail.model

import com.jnasser.core.domain.constants.DefaultValues.EMPTY_INT
import com.jnasser.core.domain.constants.DefaultValues.EMPTY_STRING

data class UVDataUi(
    val uvValue: Int = EMPTY_INT,
    val state: String = EMPTY_STRING,
    val preventUVHours: List<String> = emptyList()
)

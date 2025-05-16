package com.jnasser.weather.presentation.weather_detail.model

import com.jnasser.core.domain.constants.DefaultValues.EMPTY_STRING

data class WindDataUi(
    val title: String = EMPTY_STRING,
    val direction: String = EMPTY_STRING,
    val velocity: String = EMPTY_STRING,
    val gust: String? = null
)

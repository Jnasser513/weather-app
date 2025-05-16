package com.jnasser.core.domain.city

import com.jnasser.core.domain.constants.DefaultValues.EMPTY_LONG
import com.jnasser.core.domain.constants.DefaultValues.EMPTY_STRING

data class Weather(
    val id: Long = EMPTY_LONG,
    val main: String = EMPTY_STRING,
    val description: String = EMPTY_STRING,
    val icon: String = EMPTY_STRING
)

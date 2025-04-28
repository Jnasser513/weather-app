package com.jnasser.core.presentation.ui.utils

import com.jnasser.core.domain.enums.TemperatureUnitsEnum

fun TemperatureUnitsEnum.format(value: Double) = "%.${decimals}f %s".format(value, symbol)
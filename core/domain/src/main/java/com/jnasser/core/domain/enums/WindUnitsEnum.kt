package com.jnasser.core.domain.enums

enum class WindUnitsEnum(val symbol: String, val decimals: Int) {
    MILES(symbol = "mph", decimals = 1),
    KILOMETERS(symbol = "km/h", decimals = 1),
    METERS(symbol = "m/s", decimals = 1)
}
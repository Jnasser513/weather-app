package com.jnasser.core.domain.enums

enum class TemperatureUnitsEnum(val symbol: String, val pseudoName: String, val decimals: Int) {
    STANDARD(symbol = "K", pseudoName = "Kelvin", decimals = 2),
    METRIC(symbol = "°C", pseudoName = "Celsius", decimals = 1),
    IMPERIAL(symbol = "°F", pseudoName = "Fahrenheit", decimals = 1)
}
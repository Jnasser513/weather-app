package com.jnasser.weather.domain

enum class TemperatureUnitsEnum(val symbol: String, val pseudoName: String) {
    STANDARD(symbol = "K", pseudoName = "Kelvin"),
    METRIC(symbol = "°C", pseudoName = "Celsius"),
    IMPERIAL(symbol = "°F", pseudoName = "Fahrenheit");

    fun formatTemperature(value: Double, decimals: Int = 1) =
        "%.${decimals}f %s".format(value, symbol)
}
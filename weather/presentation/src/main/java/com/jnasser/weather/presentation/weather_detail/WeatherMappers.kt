package com.jnasser.weather.presentation.weather_detail

import com.jnasser.core.domain.util.DateUtils
import com.jnasser.core.domain.weather.model.WeatherCurrentDetail
import com.jnasser.core.domain.weather.model.WeatherDailyDetail
import com.jnasser.weather.presentation.weather_detail.model.AirQualityDataUi
import com.jnasser.weather.presentation.weather_detail.model.ForecastDataUi
import com.jnasser.weather.presentation.weather_detail.model.WindDataUi
import kotlin.math.roundToInt

fun WeatherDailyDetail.toForecastDataUi(
    symbol: String,
    progress: Float? = null,
    currentTemp: Float? = null
) = ForecastDataUi(
    title = if(DateUtils.isToday(dt)) "Today" else DateUtils.getDayNameFromTimeStamp(dt),
    icon = "https://openweathermap.org/img/wn/${weather.getOrNull(0)?.icon}@2x.png",
    progress = progress,
    currentTemperature = currentTemp,
    maxTemperature = "${temp.max?.roundToInt()}$symbol",
    minTemperature = "${temp.min?.roundToInt()}$symbol"
)

fun WeatherCurrentDetail?.toWindDataUi(windTitle: String, windDirection: String) = WindDataUi(
    title = windTitle,
    direction = windDirection,
    this?.windSpeed?.toString().orEmpty(),
    this?.windGust?.toString().orEmpty()
)


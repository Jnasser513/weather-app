package com.jnasser.weather.presentation.weather_detail.model

import com.jnasser.core.domain.util.DateUtils
import com.jnasser.core.domain.weather.model.WeatherCurrentDetail
import com.jnasser.core.domain.weather.model.WeatherDailyDetail
import com.jnasser.core.domain.weather.model.WeatherHourlyDetail
import java.time.format.TextStyle
import kotlin.math.roundToInt

fun WeatherDailyDetail.toForecastDataUi(
    symbol: String,
    progress: Float? = null,
    currentTemp: Float? = null
) = ForecastDataUi(
    dt = dt,
    title = if(DateUtils.isToday(dt)) "Today" else DateUtils.getDayNameFromTimeStamp(dt),
    icon = "https://openweathermap.org/img/wn/${weather.getOrNull(0)?.icon}@2x.png",
    progress = progress,
    currentTemperature = currentTemp,
    maxTemperature = "${temp.max?.roundToInt()}$symbol",
    minTemperature = "${temp.min?.roundToInt()}$symbol"
)

fun WeatherCurrentDetail.toWindDataUi() = WindDataUi(
    velocity = windSpeed.toString(),
    gust = windGust.toString()
)

fun WeatherDailyDetail.toWindDataUi() = WindDataUi(
    velocity = windSpeed.toString(),
    gust = windGust.toString()
)

fun WeatherHourlyDetail.toWindDataUi() = WindDataUi(
    velocity = windSpeed.toString(),
    gust = windGust.toString()
)

fun WeatherCurrentDetail.toWeatherDataUi() = WeatherDataUi(
    isCurrent = true,
    day = "Today",
    currentTemp = temp,
    weatherDescription = weather.getOrNull(0)?.description.orEmpty(),
    temperatureFeelsLike = feelsLike,
    windData = this.toWindDataUi(),
    windSpeed = windSpeed,
    windDeg = windDeg
)

fun WeatherDailyDetail.toWeatherDataUi() = WeatherDataUi(
    isCurrent = DateUtils.isToday(dt),
    day = DateUtils.getDayNameFromTimeStamp(dt, TextStyle.FULL),
    maxTemp = temp.max,
    minTemp = temp.min,
    weatherDescription = weather.getOrNull(0)?.description.orEmpty(),
    windData = this.toWindDataUi(),
    windSpeed = windSpeed,
    windDeg = windDeg
)

fun WeatherHourlyDetail.toWeatherDataUi() = WeatherDataUi(
    isCurrent = DateUtils.isToday(dt),
    day = DateUtils.getDayNameFromTimeStamp(dt, TextStyle.FULL),
    currentTemp = temp,
    weatherDescription = weather.getOrNull(0)?.description.orEmpty(),
    windData = this.toWindDataUi(),
    windSpeed = windSpeed,
    windDeg = windDeg
)


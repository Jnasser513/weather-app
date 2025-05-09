package com.jnasser.weather.presentation.weather_detail

import com.jnasser.core.domain.util.DateUtils
import com.jnasser.core.domain.weather.model.WeatherDailyDetail
import com.jnasser.weather.presentation.weather_detail.model.ForecastDataUi
import kotlin.math.roundToInt

fun WeatherDailyDetail.toForecastDataUi() = ForecastDataUi(
    title = if(DateUtils.isToday(dt)) "Today" else DateUtils.getDayNameFromTimeStamp(dt),
    icon = "https://openweathermap.org/img/wn/${weather.getOrNull(0)?.icon}@2x.png",
    maxTemperature = temp.max?.roundToInt().toString(),
    minTemperature = temp.min?.roundToInt().toString(),
    progress = 0.9f
)
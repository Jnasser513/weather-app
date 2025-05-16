package com.jnasser.weather.network.datasources

import com.jnasser.core.domain.weather.model.Rain
import com.jnasser.core.domain.weather.model.Snow
import com.jnasser.core.domain.weather.model.Temperature
import com.jnasser.core.domain.weather.model.Weather
import com.jnasser.core.domain.weather.model.WeatherCurrentDetail
import com.jnasser.core.domain.weather.model.WeatherDailyDetail
import com.jnasser.core.domain.weather.model.WeatherDetail
import com.jnasser.core.domain.weather.model.WeatherHourlyDetail
import com.jnasser.weather.network.datasources.model.RainDto
import com.jnasser.weather.network.datasources.model.SnowDto
import com.jnasser.weather.network.datasources.model.TemperatureDto
import com.jnasser.weather.network.datasources.model.WeatherCurrentDetailDto
import com.jnasser.weather.network.datasources.model.WeatherDailyDetailDto
import com.jnasser.weather.network.datasources.model.WeatherDetailDto
import com.jnasser.weather.network.datasources.model.WeatherDto
import com.jnasser.weather.network.datasources.model.WeatherHourlyDetailDto

fun WeatherDetailDto.toWeatherDetail() = WeatherDetail(
    lat = lat,
    lon = lon,
    current = current.toWeatherCurrentDetail(),
    hourly = hourly.map { it.toWeatherHourlyDetail() },
    daily = daily.map { it.toWeatherDailyDetail() }
)

fun WeatherCurrentDetailDto.toWeatherCurrentDetail() = WeatherCurrentDetail(
    dt = dt,
    sunrise = sunrise,
    sunset = sunset,
    temp = temp,
    feelsLike = feelsLike,
    pressure = pressure,
    humidity = humidity,
    uvi = uvi,
    clouds = clouds,
    visibility = visibility,
    windSpeed = windSpeed,
    windDeg = windDeg,
    windGust = windGust,
    weather = weather.map { it.toWeather() },
    rain = rain?.toRain(),
    snow = snow?.toSnow()
)

fun WeatherHourlyDetailDto.toWeatherHourlyDetail() = WeatherHourlyDetail(
    dt = dt,
    temp = temp,
    feelsLike = feelsLike,
    pressure = pressure,
    humidity = humidity,
    uvi = uvi,
    clouds = clouds,
    visibility = visibility,
    windSpeed = windSpeed,
    windDeg = windDeg,
    windGust = windGust,
    weather = weather.map { it.toWeather() },
    rain = rain?.toRain(),
    snow = snow?.toSnow(),
    pop = pop
)

fun WeatherDailyDetailDto.toWeatherDailyDetail() = WeatherDailyDetail(
    dt = dt,
    temp = temp.toTemperature(),
    feelsLike = feelsLike.toTemperature(),
    pressure = pressure,
    humidity = humidity,
    uvi = uvi,
    clouds = clouds,
    windSpeed = windSpeed,
    windDeg = windDeg,
    windGust = windGust,
    weather = weather.map { it.toWeather() },
    rain = rain,
    snow = snow,
    pop = pop,
    sunrise = sunrise,
    sunset = sunset,
    moonrise = moonrise,
    moonPhase = moonPhase,
    moonset = moonset,
    summary = summary
)

fun WeatherDto.toWeather() = Weather(
    id = id,
    main = main,
    description = description,
    icon = icon
)

fun RainDto.toRain() = Rain(
    value = value
)

fun SnowDto.toSnow() = Snow(
    value = value
)

fun TemperatureDto.toTemperature() = Temperature(
    day = day,
    min = min,
    max = max,
    night = night,
    eve = eve,
    morn = morn
)
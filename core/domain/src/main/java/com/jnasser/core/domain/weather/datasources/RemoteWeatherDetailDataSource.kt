package com.jnasser.core.domain.weather.datasources

import com.jnasser.core.domain.util.error_handler.DataError
import com.jnasser.core.domain.util.result_handler.Result
import com.jnasser.core.domain.weather.model.WeatherDetail

interface RemoteWeatherDetailDataSource {
    suspend fun getWeatherDetail(
        lat: Double,
        lon: Double,
        units: String
    ): Result<WeatherDetail, DataError.Network>
}
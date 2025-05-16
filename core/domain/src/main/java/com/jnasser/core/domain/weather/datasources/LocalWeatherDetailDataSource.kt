package com.jnasser.core.domain.weather.datasources

import com.jnasser.core.domain.util.error_handler.DataError
import com.jnasser.core.domain.util.result_handler.Result
import com.jnasser.core.domain.weather.model.WeatherDetail
import kotlinx.coroutines.flow.Flow

interface LocalWeatherDetailDataSource {
    fun getWeatherDetail(
        id: Long
    ): Flow<WeatherDetail>

    fun saveWeatherDetail(
        weatherDetail: WeatherDetail
    ): Result<Long, DataError.Local>
}
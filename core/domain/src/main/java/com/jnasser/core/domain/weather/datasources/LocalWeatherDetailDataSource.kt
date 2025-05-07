package com.jnasser.core.domain.weather_detail

import com.jnasser.core.domain.util.error_handler.DataError
import com.jnasser.core.domain.util.result_handler.Result
import com.jnasser.core.domain.weather_detail.model.WeatherDetail
import kotlinx.coroutines.flow.Flow

interface LocalWeatherDetailDataSource {
    suspend fun getWeatherDetail(
        id: Long
    ): Flow<WeatherDetail>
}
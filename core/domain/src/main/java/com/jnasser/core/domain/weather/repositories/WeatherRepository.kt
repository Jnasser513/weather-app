package com.jnasser.core.domain.weather.repositories

import com.jnasser.core.domain.util.error_handler.DataError
import com.jnasser.core.domain.util.result_handler.EmptyResult
import com.jnasser.core.domain.util.result_handler.Result
import com.jnasser.core.domain.weather.model.WeatherDetail
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun getWeatherDetail(id: Long): Flow<WeatherDetail>

    suspend fun upsertWeatherDetail(
        lat: Double,
        lon: Double,
        units: String
    ): Result<Long, DataError>

}
package com.jnasser.core.data.weather_detail.networking.weather.repositories

import com.jnasser.core.domain.util.error_handler.DataError
import com.jnasser.core.domain.util.result_handler.Result
import com.jnasser.core.domain.weather.datasources.LocalWeatherDetailDataSource
import com.jnasser.core.domain.weather.datasources.RemoteWeatherDetailDataSource
import com.jnasser.core.domain.weather.model.WeatherDetail
import com.jnasser.core.domain.weather.repositories.WeatherRepository
import kotlinx.coroutines.flow.Flow

class OfflineFirstWeatherRepository(
    private val localWeatherDetailDataSource: LocalWeatherDetailDataSource,
    private val remoteWeatherDetailDataSource: RemoteWeatherDetailDataSource
) : WeatherRepository {

    override fun getWeatherDetail(id: Long): Flow<WeatherDetail> =
        localWeatherDetailDataSource.getWeatherDetail(id)

    override suspend fun upsertWeatherDetail(
        lat: Double,
        lon: Double,
        units: String
    ): Result<Long, DataError> {
        return when (val result = remoteWeatherDetailDataSource.getWeatherDetail(lat, lon, units)) {
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> {
                when (val saveResult =
                    localWeatherDetailDataSource.saveWeatherDetail(result.data)) {
                    is Result.Error -> Result.Error(saveResult.error)
                    is Result.Success -> saveResult
                }
            }
        }
    }
}
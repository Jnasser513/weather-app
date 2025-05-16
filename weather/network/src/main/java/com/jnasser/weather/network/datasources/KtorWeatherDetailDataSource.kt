package com.jnasser.weather.network.datasources

import com.jnasser.core.data.weather_detail.networking.get
import com.jnasser.core.domain.util.error_handler.DataError
import com.jnasser.core.domain.util.result_handler.Result
import com.jnasser.core.domain.util.result_handler.map
import com.jnasser.core.domain.weather.datasources.RemoteWeatherDetailDataSource
import com.jnasser.core.domain.weather.model.WeatherDetail
import com.jnasser.weather.network.datasources.model.WeatherDetailDto
import io.ktor.client.HttpClient

class KtorWeatherDetailDataSource(
    private val httpClient: HttpClient,
    private val apiKey: String
): RemoteWeatherDetailDataSource {

    override suspend fun getWeatherDetail(
        lat: Double,
        lon: Double,
        units: String
    ): Result<WeatherDetail, DataError.Network> {
        return httpClient.get<WeatherDetailDto>(
            queryParameters = mapOf(
                "lat" to lat,
                "lon" to lon,
                "units" to units,
                "exclude" to "minutely",
                "appid" to apiKey
            )
        ).map { it.toWeatherDetail() }
    }
}
package com.jnasser.core.domain.weather_detail

import com.jnasser.core.domain.util.result_handler.Result

interface RemoteWeatherDetailDataSource {

    suspend fun getWeatherDetail(): Result<>
}
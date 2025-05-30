package com.jnasser.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jnasser.core.domain.city.CityDetail
import com.jnasser.core.domain.city.Weather
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme
import com.jnasser.weather.presentation.weather_detail.WeatherDetailState
import com.jnasser.weather.presentation.weather_detail.composables.WeatherDetailScreen
import com.jnasser.weather.presentation.weather_detail.composables.WeatherDetailScreenRoot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                WeatherDetailScreenRoot()
            }
        }
    }
}
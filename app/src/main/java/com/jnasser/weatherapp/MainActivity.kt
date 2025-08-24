package com.jnasser.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.city.presentation.city_saved_list.components.CitySavedListScreenRoot
import com.jnasser.core.domain.city.CityDetail
import com.jnasser.core.domain.city.Weather
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme
import com.jnasser.weather.presentation.weather_detail.WeatherDetailState
import com.jnasser.weather.presentation.weather_detail.composables.WeatherDetailScreen
import com.jnasser.weather.presentation.weather_detail.composables.WeatherDetailScreenRoot
import com.jnasser.weatherapp.navigation.NavigationRoot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                val navController = rememberNavController()

                NavigationRoot(navController)
            }
        }
    }
}
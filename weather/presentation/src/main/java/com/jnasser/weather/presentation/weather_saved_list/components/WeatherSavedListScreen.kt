package com.jnasser.weather.presentation.weather_saved_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme
import com.jnasser.weather.presentation.R
import com.jnasser.weather.presentation.weather_saved_list.WeatherSavedListAction
import com.jnasser.weather.presentation.weather_saved_list.WeatherSavedListState
import com.jnasser.weather.presentation.weather_saved_list.WeatherSavedListViewModel
import com.jnasser.weather.presentation.weather_saved_list.model.CitySummary
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeatherSavedListScreenRoot(
    viewModel: WeatherSavedListViewModel = koinViewModel(),
    onCityDetail: (Long) -> Unit
) {
    WeatherSavedListScreen(
        state = viewModel.state,
        onAction = { action ->
            when(action) {
                is WeatherSavedListAction.OnWeatherDetail -> onCityDetail(action.id)
            }
        }
    )
}

@Composable
fun WeatherSavedListScreen(
    state: WeatherSavedListState,
    onAction: (WeatherSavedListAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF1C1C2D), Color(0xFF2E2E3D))
                )
            )
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.saved_cities),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(state.cities) { city ->
                CityCard(city = city, onClick = { onAction(WeatherSavedListAction.OnWeatherDetail(city.id)) })
            }
        }
    }
}

@Preview
@Composable
private fun WeatherSavedListScreenPreview() {
    WeatherAppTheme {
        WeatherSavedListScreen(
            WeatherSavedListState(
                isLoading = false,
                cities = listOf(
                    CitySummary(
                        id = 1,
                        name = "San Diego",
                        temperature = "10.8",
                        condition = "Too Cold"
                    )
                )
            )
        ) {}
    }
}
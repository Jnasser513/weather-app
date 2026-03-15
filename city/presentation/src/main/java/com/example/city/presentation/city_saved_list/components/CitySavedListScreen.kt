@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.city.presentation.city_saved_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.city.presentation.R
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme
import com.example.city.presentation.city_saved_list.CitySavedListAction
import com.example.city.presentation.city_saved_list.CitySavedListState
import com.example.city.presentation.city_saved_list.CitySavedListViewModel
import com.example.city.presentation.city_saved_list.model.CitySummary
import com.jnasser.core.presentation.designsystem.components.WeatherAppFAB
import com.jnasser.core.presentation.designsystem.components.WeatherAppFABConfig
import com.jnasser.core.presentation.designsystem.components.WeatherAppScaffold
import org.koin.androidx.compose.koinViewModel

@Composable
fun CitySavedListScreenRoot(
    viewModel: CitySavedListViewModel = koinViewModel(),
    onCityDetail: (Long) -> Unit,
    onCitySearch: () -> Unit
) {
    CitySavedListScreen(
        state = viewModel.state,
        onAction = { action ->
            when(action) {
                is CitySavedListAction.OnCityDetail -> onCityDetail(action.id)
                CitySavedListAction.OnSearchCity -> onCitySearch()
            }
        }
    )
}

@Composable
fun CitySavedListScreen(
    state: CitySavedListState,
    onAction: (CitySavedListAction) -> Unit
) {
    WeatherAppScaffold(
        modifier = Modifier
            .fillMaxSize(),
        withGradient = false,
        floatingActionButton = {
            WeatherAppFAB(
                config = WeatherAppFABConfig(
                    onclick = {
                        onAction(CitySavedListAction.OnSearchCity)
                    }
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF1C1C2D), Color(0xFF2E2E3D))
                    )
                )
                .padding(horizontal = 16.dp)
                .systemBarsPadding()
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
                    CityCard(city = city, onClick = { onAction(CitySavedListAction.OnCityDetail(city.id)) })
                }
            }
        }
    }
}

@Preview
@Composable
private fun CitySavedListScreenPreview() {
    WeatherAppTheme {
        CitySavedListScreen(
            CitySavedListState(
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

@Preview
@Composable
private fun WeatherEmptySavedListScreenPreview() {
    WeatherAppTheme {
        CitySavedListScreen(
            CitySavedListState(
                isLoading = false,
                cities = emptyList()
            )
        ) {}
    }
}
@file:OptIn(ExperimentalMaterial3Api::class)

package com.jnasser.weather.presentation.weather_detail.composables

import WeatherAppAnimatedSwipeableButton
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jnasser.core.domain.city.CityDetail
import com.jnasser.core.domain.city.Weather
import com.jnasser.core.domain.weather.model.WeatherDetail
import com.jnasser.core.presentation.designsystem.components.AnimatedText
import com.jnasser.core.presentation.designsystem.components.animations.SequentialAnimatedItems
import com.jnasser.core.presentation.designsystem.components.WeatherAppScaffold
import com.jnasser.core.presentation.designsystem.components.WeatherTopAppBar
import com.jnasser.core.presentation.designsystem.components.WeatherTopAppBarConfig
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme
import com.jnasser.weather.presentation.R
import com.jnasser.weather.presentation.weather_detail.WeatherDetailAction
import com.jnasser.weather.presentation.weather_detail.WeatherDetailState
import com.jnasser.weather.presentation.weather_detail.WeatherDetailViewModel
import com.jnasser.weather.presentation.weather_detail.composables.forecast.ForecastContainer
import com.jnasser.weather.presentation.weather_detail.composables.wind.WindContainer
import com.jnasser.weather.presentation.weather_detail.model.WindDataUi
import com.jnasser.weather.presentation.weather_detail.toForecastDataUi
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeatherDetailScreenRoot(
    viewModel: WeatherDetailViewModel = koinViewModel()
) {
    WeatherDetailScreen(state = viewModel.state, onAction = { action ->
        when (action) {
            WeatherDetailAction.OnGoHome -> TODO()
            WeatherDetailAction.OnMapDetail -> TODO()
            else -> Unit
        }
        viewModel.onAction(action)
    })
}

@Composable
fun WeatherDetailScreen(
    state: WeatherDetailState, onAction: (WeatherDetailAction) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    var showFab by remember { mutableStateOf(false) }

    WeatherAppScaffold(
        topApBar = {
            WeatherTopAppBar(
                config = WeatherTopAppBarConfig(
                    title = "San Francisco, CA",
                    centerTitle = true,
                    navigationIcon = WeatherTopAppBar.NavigationIcon.Custom(icon = Icons.Outlined.Home,
                        click = {

                        }),
                    actions = listOf(
                        WeatherTopAppBar.Action(text = stringResource(R.string.map),
                            icon = com.jnasser.core.presentation.designsystem.theme.Icons.Map,
                            onClick = {

                            })
                    ),

                    scrollBehavior = scrollBehavior
                )
            )
        },
        scrollBehavior = scrollBehavior,
        floatingActionButton = {
            if(showFab) {
                WeatherAppAnimatedSwipeableButton(buttonText = stringResource(com.jnasser.core.presentation.designsystem.R.string.follow_up),
                    buttonTextAlternative = stringResource(com.jnasser.core.presentation.designsystem.R.string.unfollow_up),
                    draggableIconActive = {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = null,
                            tint = Color(0x6641588A)
                        )
                    },
                    draggableIconInactive = {
                        Icon(
                            imageVector = Icons.Filled.FavoriteBorder,
                            contentDescription = null,
                            tint = Color(0x6641588A)
                        )
                    }) {
                    onAction(WeatherDetailAction.OnFollowUp(CityDetail()))
                }
            }
        }) { padding ->
        SequentialAnimatedItems(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            items = listOf(
                {
                    val text = stringResource(
                        R.string.temperature_description,
                        state.weather.current?.temp.toString(),
                        state.weather.current?.weather?.getOrNull(0)?.main.orEmpty(),
                        state.weather.current?.feelsLike.toString()
                    )
                    AnimatedText(
                        text = text,
                        highlightWordPositions = listOf(2, 4, 5)
                    )
                    Spacer(Modifier.height(40.dp))
                },
                {
                    ForecastContainer(
                        forecastList = state.weather.daily?.map { it.toForecastDataUi() }.orEmpty()
                    )
                    Spacer(Modifier.height(30.dp))
                },
                {
                    WindContainer(
                        windDataUi = WindDataUi(
                            "Gentle Breeze", "ESE", "9", "14"
                        ), windUnit = state.windUnit, onAction = onAction
                    )
                    Spacer(Modifier.height(10.dp))
                },
                {
                    ExtraDataComponents()
                }
            ),
            onSequenceEnd = {
                showFab = true
            }
        )
        /*LazyColumn(
            contentPadding = PaddingValues(20.dp)
        ) {
            item {
                val text = stringResource(
                    R.string.temperature_description,
                    state.city.temperature,
                    state.city.weather.description,
                    state.city.temperatureFeels
                )
                AnimatedText(
                    text = text,
                    highlightWordPositions = listOf(2, 4, 5)
                )
                Spacer(Modifier.height(40.dp))
            }

            item {
                ForecastContainer()
                Spacer(Modifier.height(30.dp))
            }

            *//*item {
                WindContainer(
                    windDataUi = WindDataUi(
                        "Gentle Breeze", "ESE", "9", "14"
                    ), windUnit = state.windUnit, onAction = onAction
                )
                Spacer(Modifier.height(10.dp))
            }

            item {
                Row(
                    modifier = Modifier.fillMaxSize()
                ) {
                    UVContainer(
                        modifier = Modifier.weight(1f), uvDataUi = UVDataUi(
                            uvValue = 1,
                            state = "Low",
                            preventUVHours = listOf("12pm", "1pm", "2pm", "3pm")
                        )
                    )
                    Spacer(Modifier.width(15.dp))
                    AirQualityContainer(
                        modifier = Modifier.weight(1f), airQualityDataUi = AirQualityDataUi(
                            airQuality = 1,
                            airCo = 201.94053649902344,
                            airNO2 = 0.7711350917816162,
                            o3 = 68.66455078125
                        )
                    )
                }
            }*//*
        }*/
    }
}


@Preview
@Composable
private fun WeatherDetailScreenPreview() {
    WeatherAppTheme {
        WeatherDetailScreen(
            WeatherDetailState(
                isLoading = false, weather = WeatherDetail()
            )
        ) { }
    }
}
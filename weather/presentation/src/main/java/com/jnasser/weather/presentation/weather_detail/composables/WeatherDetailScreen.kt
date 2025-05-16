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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jnasser.core.domain.constants.DefaultValues.EMPTY_STRING
import com.jnasser.core.domain.city.CityDetail
import com.jnasser.core.domain.util.DateUtils
import com.jnasser.core.domain.weather.model.WeatherDetail
import com.jnasser.core.presentation.designsystem.components.AnimatedText
import com.jnasser.core.presentation.designsystem.components.animations.SequentialAnimatedItems
import com.jnasser.core.presentation.designsystem.components.WeatherAppScaffold
import com.jnasser.core.presentation.designsystem.components.WeatherTopAppBar
import com.jnasser.core.presentation.designsystem.components.WeatherTopAppBarConfig
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme
import com.jnasser.core.presentation.ui.utils.extensions.getLocalizedWindDescription
import com.jnasser.core.presentation.ui.utils.extensions.getWindDirectionFromDegrees
import com.jnasser.weather.presentation.R
import com.jnasser.weather.presentation.weather_detail.WeatherDetailAction
import com.jnasser.weather.presentation.weather_detail.WeatherDetailState
import com.jnasser.weather.presentation.weather_detail.WeatherDetailViewModel
import com.jnasser.weather.presentation.weather_detail.composables.forecast.ForecastContainer
import com.jnasser.weather.presentation.weather_detail.composables.wind.WindContainer
import com.jnasser.weather.presentation.weather_detail.model.UVDataUi
import com.jnasser.weather.presentation.weather_detail.model.WindDataUi
import com.jnasser.weather.presentation.weather_detail.toForecastDataUi
import com.jnasser.weather.presentation.weather_detail.toWindDataUi
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
    val context = LocalContext.current
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
                        "${state.weatherSelection.currentTemp}${state.temperatureUnits.symbol}",
                        state.weatherSelection.weatherDescription.orEmpty(),
                        "${state.weatherSelection.temperatureFeelsLike}${state.temperatureUnits.symbol}"
                    )
                    AnimatedText(
                        text = text,
                        highlightWordPositions = listOf(2, 4, 5)
                    )
                    Spacer(Modifier.height(40.dp))
                },
                {
                    val today = state.weather.daily?.first() { DateUtils.isToday(it.dt) }
                    val forecastList = state.weather.daily?.map { forecast ->
                        if(forecast == today) {
                            val minTemp = forecast.temp.min ?: 0f
                            val maxTemp = forecast.temp.max ?: 0f
                            val currentTemp = state.weather.current?.temp

                            val progress = if (currentTemp != null && maxTemp != minTemp) {
                                ((currentTemp - minTemp) / (maxTemp - minTemp)).coerceIn(0f, 1f)
                            } else {
                                0f
                            }
                            forecast.toForecastDataUi(state.temperatureUnits.symbol, progress, currentTemp)
                        }
                        else forecast.toForecastDataUi(state.temperatureUnits.symbol)
                    }

                    ForecastContainer(
                        forecastList = forecastList.orEmpty()
                    )
                    Spacer(Modifier.height(30.dp))
                },
                {
                    val windTitle = context.getLocalizedWindDescription(state.weatherSelection.windSpeed)
                    val windDirection = context.getWindDirectionFromDegrees(state.weatherSelection.windDeg)
                    WindContainer(
                        windDataUi = state.weatherSelection.windData.copy(
                            title = windTitle,
                            direction = windDirection
                        ),
                        windUnit = state.windUnit,
                        onAction = onAction
                    )
                    Spacer(Modifier.height(10.dp))
                },
                {
                    ExtraDataComponents(
                        uvDataUi = UVDataUi(
                            uvValue = 1,
                            state = "Low",
                            preventUVHours = listOf("12pm", "1pm", "2pm", "3pm")
                        )
                    )
                }
            ),
            onSequenceEnd = {
                showFab = true
            }
        )
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
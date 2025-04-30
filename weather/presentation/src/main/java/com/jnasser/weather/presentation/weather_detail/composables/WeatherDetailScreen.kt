package com.jnasser.weather.presentation.weather_detail.composables

import WeatherAppAnimatedSwipeableButton
import WeatherAppSwipeableButton
import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jnasser.core.domain.city.CityDetail
import com.jnasser.core.domain.city.Weather
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
    state: WeatherDetailState,
    onAction: (WeatherDetailAction) -> Unit
) {
    WeatherAppScaffold(topApBar = {
        WeatherTopAppBar(
            config = WeatherTopAppBarConfig(
                title = state.city.name,
                centerTitle = true,
                navigationIcon = WeatherTopAppBar.NavigationIcon.Custom(
                    icon = Icons.Outlined.Home,
                    click = {

                    }),
                actions = listOf(
                    WeatherTopAppBar.Action(text = stringResource(R.string.map),
                        icon = com.jnasser.core.presentation.designsystem.theme.Icons.Map,
                        onClick = {

                        })
                )
            )
        )
    }, floatingActionButton = {
        WeatherAppAnimatedSwipeableButton(
            buttonText = stringResource(com.jnasser.core.presentation.designsystem.R.string.follow_up),
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
            }
        ) {

        }
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
        ) {
            val text = stringResource(
                R.string.temperature_description,
                state.city.temperature,
                state.city.weather.description,
                state.city.temperatureFeels
            )/*AnimatedText(
                text = text,
                highlightWordPositions = listOf(0,1, 4,6,7)
            )*/
            Text(
                text = text,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(40.dp))
            ForecastContainer()
            Spacer(Modifier.height(30.dp))
            WindContainer(
                windDataUi = WindDataUi(
                    "Gentle Breeze",
                    "ESE",
                    "9",
                    "14"
                ),
                windUnit = state.windUnit,
                onAction = onAction
            )
        }
    }
}


@Preview
@Composable
private fun WeatherDetailScreenPreview() {
    WeatherAppTheme {
        WeatherDetailScreen(
            WeatherDetailState(
                isLoading = false, city = CityDetail(
                    name = "San Francisco, CA",
                    temperature = "50",
                    temperatureFeels = "53",
                    weather = Weather(description = "partly cloudy")
                )
            )
        ) { }
    }
}
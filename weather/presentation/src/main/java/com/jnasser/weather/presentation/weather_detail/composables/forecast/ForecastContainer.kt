package com.jnasser.weather.presentation.weather_detail.composables.forecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jnasser.core.presentation.designsystem.components.animations.AnimatedContent
import com.jnasser.core.presentation.designsystem.components.animations.WeatherMotionTokens
import com.jnasser.core.presentation.designsystem.components.animations.rememberWeatherMotionSettings
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme
import com.jnasser.weather.domain.repositories.ForecastSelection
import com.jnasser.weather.presentation.R
import com.jnasser.weather.presentation.weather_detail.model.ForecastDataUi

@Composable
fun ForecastContainer(
    modifier: Modifier = Modifier,
    forecastList: List<ForecastDataUi>,
    selectedToggle: ForecastSelection = ForecastSelection.DAILY,
    onDailyClick: () -> Unit,
    onHourlyClick: () -> Unit,
    selectedItem: (Long) -> Unit,
    onForecastAnimationsComplete: () -> Unit = {}
) {
    val motion = rememberWeatherMotionSettings()
    var showForecastList by rememberSaveable(forecastList.size) {
        mutableStateOf(!motion.animationsEnabled)
    }
    val enterDuration = motion.durationMillis(WeatherMotionTokens.Long)

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        AnimatedContent(
            visible = true,
            enterAnim = fadeIn(
                animationSpec = tween(
                    durationMillis = enterDuration,
                    easing = FastOutSlowInEasing
                )
            ) + slideInVertically(
                initialOffsetY = { it / 4 },
                animationSpec = tween(
                    durationMillis = enterDuration,
                    easing = FastOutSlowInEasing
                )
            ),
            onShown = {
                showForecastList = true
            },
            content = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.forecast),
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.surface
                        )
                    )
                    ForecastOptions(
                        selectedToggle = selectedToggle,
                        onDailyClick = onDailyClick,
                        onHourlyClick = onHourlyClick
                    )
                }
                Spacer(Modifier.height(10.dp))
            }
        )

        if(showForecastList) {
            ForecastList(
                forecastList = forecastList,
                selectedItem = selectedItem,
                onAnimationsComplete = onForecastAnimationsComplete
            )
        }
    }
}

@Preview
@Composable
private fun ForecastContainerPreview() {
    WeatherAppTheme {
        ForecastContainer(
            forecastList = listOf(
                ForecastDataUi(
                    dt = 1,
                    title = "Today",
                    icon = "https://openweathermap.org/img/wn/10d@2x.png",
                    maxTemperature = "48",
                    minTemperature = "56",
                    progress = 0.25f
                ),
                ForecastDataUi(
                    dt = 2,
                    title = "Thu",
                    icon = "https://openweathermap.org/img/wn/10d@2x.png",
                    maxTemperature = "48",
                    minTemperature = "56",
                    progress = 0.48f
                ),
                ForecastDataUi(
                    dt = 3,
                    title = "Fri",
                    icon = "https://openweathermap.org/img/wn/10d@2x.png",
                    maxTemperature = "48",
                    minTemperature = "56",
                    progress = 0.91f
                )
            ),
            selectedItem = {},
            onDailyClick = {},
            onHourlyClick = {}
        )
    }
}

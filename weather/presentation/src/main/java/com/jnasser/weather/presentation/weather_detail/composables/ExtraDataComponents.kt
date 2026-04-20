package com.jnasser.weather.presentation.weather_detail.composables

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jnasser.core.presentation.designsystem.components.animations.AnimatedContent
import com.jnasser.core.presentation.designsystem.components.animations.WeatherMotionTokens
import com.jnasser.core.presentation.designsystem.components.animations.rememberWeatherMotionSettings
import com.jnasser.weather.presentation.weather_detail.composables.air_quality.AirQualityContainer
import com.jnasser.weather.presentation.weather_detail.composables.uv.UVContainer
import com.jnasser.weather.presentation.weather_detail.model.AirQualityDataUi
import com.jnasser.weather.presentation.weather_detail.model.UVDataUi

@Composable
fun ExtraDataComponents(
    modifier: Modifier = Modifier,
    uvDataUi: UVDataUi
) {
    val motion = rememberWeatherMotionSettings()

    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        AnimatedContent(
            visible = true,
            enterAnim = fadeIn(
                animationSpec = tween(
                    durationMillis = motion.durationMillis(WeatherMotionTokens.Long),
                    easing = FastOutSlowInEasing
                )
            ) + slideInVertically(
                initialOffsetY = { it / 10 },
                animationSpec = tween(
                    durationMillis = motion.durationMillis(WeatherMotionTokens.Long),
                    easing = FastOutSlowInEasing
                )
            ),
            content = {
                Row {
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
            }
        )
    }
}

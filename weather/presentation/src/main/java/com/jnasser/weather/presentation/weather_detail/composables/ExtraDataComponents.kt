package com.jnasser.weather.presentation.weather_detail.composables

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jnasser.core.presentation.designsystem.components.animations.AnimatedContent
import com.jnasser.weather.presentation.weather_detail.composables.air_quality.AirQualityContainer
import com.jnasser.weather.presentation.weather_detail.composables.uv.UVContainer
import com.jnasser.weather.presentation.weather_detail.model.AirQualityDataUi
import com.jnasser.weather.presentation.weather_detail.model.UVDataUi

@Composable
fun ExtraDataComponents(
    modifier: Modifier = Modifier,
    uvDataUi: UVDataUi
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        AnimatedContent(
            visible = visible,
            transition = fadeIn(
                animationSpec = tween(
                    durationMillis = 500,
                    easing = FastOutSlowInEasing
                )
            ) + scaleIn(
                animationSpec = tween(
                    durationMillis = 300,
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
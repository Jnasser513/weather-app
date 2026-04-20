package com.jnasser.weather.presentation.weather_detail.composables.forecast

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jnasser.core.presentation.designsystem.components.animations.WeatherMotionTokens
import com.jnasser.core.presentation.designsystem.components.animations.rememberWeatherMotionSettings
import com.jnasser.weather.presentation.weather_detail.model.ForecastDataUi
import kotlinx.coroutines.delay

@Composable
fun ForecastList(
    modifier: Modifier = Modifier,
    forecastList: List<ForecastDataUi>,
    delayPerItem: Long = 100L,
    selectedItem: (Long) -> Unit,
    onAnimationsComplete: () -> Unit = {}
) {
    val motion = rememberWeatherMotionSettings()
    val enterDuration = motion.durationMillis(WeatherMotionTokens.Long)
    val itemDelay = motion.staggerMillis((delayPerItem + 40L).toInt())
    val forecastListKey = remember(forecastList) { forecastList.joinToString(separator = ",") { it.dt.toString() } }

    var visibleCount by rememberSaveable(forecastListKey) {
        mutableIntStateOf(if (motion.animationsEnabled) 0 else forecastList.size)
    }
    var hasReportedCompletion by rememberSaveable(forecastListKey) {
        mutableStateOf(false)
    }

    LaunchedEffect(forecastList, motion.durationScale) {
        hasReportedCompletion = false

        if (forecastList.isEmpty()) {
            visibleCount = 0
            hasReportedCompletion = true
            onAnimationsComplete()
            return@LaunchedEffect
        }

        if (!motion.animationsEnabled) {
            visibleCount = forecastList.size
            hasReportedCompletion = true
            onAnimationsComplete()
            return@LaunchedEffect
        }

        visibleCount = 0
        forecastList.indices.forEach { index ->
            visibleCount = index + 1
            if (index != forecastList.lastIndex) {
                delay(itemDelay)
            }
        }
    }

    LaunchedEffect(visibleCount, forecastList.size, hasReportedCompletion) {
        if (
            forecastList.isNotEmpty() &&
            visibleCount == forecastList.size &&
            !hasReportedCompletion
        ) {
            hasReportedCompletion = true
            onAnimationsComplete()
        }
    }

    var selectedIndex by rememberSaveable(forecastListKey) { mutableIntStateOf(0) }

    LazyRow(
        modifier = modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(
            items = forecastList,
            key = { _, item -> item.dt }
        ) { index, item ->
            AnimatedVisibility(
                visible = index < visibleCount,
                enter = fadeIn(animationSpec = tween(durationMillis = enterDuration)) +
                    slideInHorizontally(
                        initialOffsetX = { it / 6 },
                        animationSpec = tween(durationMillis = enterDuration)
                    )
            ) {
                ForecastItem(
                    forecastDataUi = item,
                    id = index,
                    isSelected = selectedIndex == index
                ) {
                    selectedIndex = index
                    selectedItem(item.dt)
                }
            }
        }
    }
}

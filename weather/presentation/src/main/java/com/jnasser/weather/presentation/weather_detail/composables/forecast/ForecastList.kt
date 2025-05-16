package com.jnasser.weather.presentation.weather_detail.composables.forecast

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jnasser.weather.presentation.weather_detail.model.ForecastDataUi
import kotlinx.coroutines.delay

@Composable
fun ForecastList(
    modifier: Modifier = Modifier,
    forecastList: List<ForecastDataUi>,
    delayPerItem: Long = 100L,
    selectedItem: (Long) -> Unit
) {
    val visibleStates = remember { mutableStateListOf<Boolean>() }

    LaunchedEffect(forecastList) {
        visibleStates.clear()
        repeat(forecastList.size) { visibleStates.add(false) }

        forecastList.indices.forEach { index ->
            delay(delayPerItem)
            visibleStates[index] = true
        }
    }

    var selectedIndex by remember { mutableIntStateOf(0) }

    LazyRow(
        modifier = modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(forecastList) { index, item ->
            val visible = visibleStates.getOrNull(index) == true

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(durationMillis = 500))
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
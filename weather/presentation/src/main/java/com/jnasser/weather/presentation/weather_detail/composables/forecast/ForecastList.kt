package com.jnasser.weather.presentation.weather_detail.composables.forecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jnasser.weather.presentation.weather_detail.model.ForecastDataUi

@Composable
fun ForecastList(
    modifier: Modifier = Modifier,
    forecastList: List<ForecastDataUi>
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(
            items = forecastList,
            key = { it.title }
        ) {
            ForecastItem(
                forecastDataUi = it,
                isSelected = false
            ) {}
        }
    }
}
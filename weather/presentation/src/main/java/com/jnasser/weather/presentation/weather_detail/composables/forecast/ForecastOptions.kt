package com.jnasser.weather.presentation.weather_detail.composables.forecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jnasser.core.presentation.designsystem.components.WeatherAppToggleButton
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme
import com.jnasser.weather.presentation.R

@Composable
fun ForecastOptions(
    modifier: Modifier = Modifier,
    onDailyClick: () -> Unit,
    onHourlyClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        WeatherAppToggleButton(
            title = stringResource(R.string.daily),
            isSelected = true,
            onClick = onDailyClick
        )
        Spacer(Modifier.width(10.dp))
        WeatherAppToggleButton(
            title = stringResource(R.string.hourly),
            isSelected = false,
            onClick = onHourlyClick
        )
    }
}

@Preview
@Composable
private fun ForecastOptionsPreview() {
    WeatherAppTheme {
        ForecastOptions(onDailyClick = {}, onHourlyClick = {})
    }
}


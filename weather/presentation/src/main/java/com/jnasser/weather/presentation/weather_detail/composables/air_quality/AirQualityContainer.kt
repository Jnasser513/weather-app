package com.jnasser.weather.presentation.weather_detail.composables.air_quality

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme
import com.jnasser.core.presentation.designsystem.theme.WeatherGrey
import com.jnasser.weather.presentation.R
import com.jnasser.weather.presentation.weather_detail.composables.DataWithProgress
import com.jnasser.weather.presentation.weather_detail.model.AirQualityDataUi
import kotlin.math.ceil
import kotlin.math.roundToInt

@Composable
fun AirQualityContainer(
    modifier: Modifier = Modifier,
    airQualityDataUi: AirQualityDataUi
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(25.dp))
            .background(Color.Transparent)
            .border(width = 2.dp, color = WeatherGrey, shape = RoundedCornerShape(25.dp))
    ) {
        // Air Quality
        DataWithProgress(
            colors = listOf(Color(0xFF6BBD2D), Color(0xFF6BBD2D)),
            title = stringResource(R.string.air_quality),
            value = airQualityDataUi.airQuality,
            progress = ((5f - airQualityDataUi.airQuality) / 4f).coerceIn(0f, 1f)
        )
        // CO
        DataWithProgress(
            colors = listOf(Color(0xFF6BBD2D), Color(0xFF6BBD2D)),
            title = stringResource(R.string.co),
            description = stringResource(R.string.data_value, airQualityDataUi.airCo),
            value = ceil(((15400f - airQualityDataUi.airCo) / 15400f).coerceIn(0.0, 1.0)).roundToInt(),
            progress = (airQualityDataUi.airCo.toFloat() / 15400f).coerceIn(0f, 1f)
        )
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 5.dp),
            thickness = 1.dp,
            color = WeatherGrey
        )
        // No2
        DataWithProgress(
            colors = listOf(Color(0xFF6BBD2D), Color(0xFF6BBD2D)),
            title = stringResource(R.string.no2),
            description = stringResource(R.string.data_value, airQualityDataUi.airNO2),
            value = ceil(((200f - airQualityDataUi.airNO2) / 200f).coerceIn(0.0, 1.0)).roundToInt(),
            progress = (airQualityDataUi.airNO2.toFloat() / 200f).coerceIn(0f, 1f)
        )
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 5.dp),
            thickness = 1.dp,
            color = WeatherGrey
        )
        // O3
        DataWithProgress(
            colors = listOf(Color(0xFF6BBD2D), Color(0xFF6BBD2D)),
            title = stringResource(R.string.o3),
            description = stringResource(R.string.data_value, airQualityDataUi.o3),
            value = ceil(((180 - airQualityDataUi.o3) / 180f).coerceIn(0.0, 1.0)).roundToInt(),
            progress = (airQualityDataUi.o3.toFloat() / 180f).coerceIn(0f, 1f)
        )
    }
}

@Preview
@Composable
private fun AirQualityContainerPreview() {
    WeatherAppTheme {
        AirQualityContainer(
            airQualityDataUi = AirQualityDataUi(
                airQuality = 1,
                airCo = 201.94053649902344,
                airNO2 = 0.7711350917816162,
                o3 = 68.66455078125
            )
        )
    }
}
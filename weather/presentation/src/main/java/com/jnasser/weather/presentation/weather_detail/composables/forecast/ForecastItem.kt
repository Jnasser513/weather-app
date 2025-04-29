package com.jnasser.weather.presentation.weather_detail.composables.forecast

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.jnasser.core.presentation.designsystem.components.WeatherAppLoading
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme
import com.jnasser.core.presentation.designsystem.theme.WeatherGrey
import com.jnasser.weather.presentation.weather_detail.model.ForecastDataUi

@Composable
fun ForecastItem(
    modifier: Modifier = Modifier,
    forecastDataUi: ForecastDataUi,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                onClick()
            }
            .background(
                color = if (isSelected) Color(0x3350538D) else Color.Transparent
            )
            .padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 10.dp),
            text = forecastDataUi.title,
            style = MaterialTheme.typography.labelLarge
        )
        WeatherIconWithProgress(
            imageUrl = forecastDataUi.icon,
            progress = forecastDataUi.progress
        )
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp)
        ) {
            Text(
                text = forecastDataUi.minTemperature,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold)
            )
            Spacer(Modifier.width(2.dp))
            Text(
                text = forecastDataUi.maxTemperature,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold)
            )
        }
    }
}

@Composable
fun WeatherIconWithProgress(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    progress: Float
) {
    Box(
        modifier = modifier.size(60.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            drawArc(
                color = WeatherGrey,
                startAngle = -225f,
                sweepAngle = 270f,
                useCenter = false,
                style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF6BBD2D),
                        Color(0xFFCE972A)
                    )
                ),
                startAngle = -225f,
                sweepAngle = 270f * progress,
                useCenter = false,
                style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
            )
        }

        SubcomposeAsyncImage(
            model = imageUrl,
            contentDescription = null,
            error = {  },
            loading = { WeatherAppLoading() }
        )
    }
}

@Preview
@Composable
private fun ForecastItemPreview() {
    WeatherAppTheme {
        ForecastItem(
            isSelected = true,
            forecastDataUi = ForecastDataUi(
                title = "Today",
                icon = "https://openweathermap.org/img/wn/10d@2x.png",
                maxTemperature = "48",
                minTemperature = "56",
                progress = 1f
            )
        ) {}
    }
}
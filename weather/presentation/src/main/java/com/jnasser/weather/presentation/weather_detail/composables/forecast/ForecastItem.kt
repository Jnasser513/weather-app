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
import com.jnasser.core.presentation.designsystem.components.WeatherContentWithProgress
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme
import com.jnasser.core.presentation.designsystem.theme.WeatherGrey
import com.jnasser.weather.presentation.weather_detail.model.ForecastDataUi

@Composable
fun ForecastItem(
    modifier: Modifier = Modifier,
    id: Int,
    forecastDataUi: ForecastDataUi,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(25.dp))
            .clickable {
                onClick()
            }
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f) else Color.Transparent
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

        WeatherContentWithProgress(
            id = id,
            progress = forecastDataUi.progress ?: 0f,
            showCircle = forecastDataUi.currentTemperature != null,
            showArcComplete = true,
            progressColors = listOf(Color(0xFF6BBD2D), Color(0xFFCE972A))
        ) {
            SubcomposeAsyncImage(
                model = forecastDataUi.icon,
                contentDescription = null,
                error = {  },
                loading = { WeatherAppLoading() }
            )
        }
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp)
        ) {
            Text(
                text = forecastDataUi.minTemperature,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
            )
            Spacer(Modifier.width(3.dp))
            Text(
                text = forecastDataUi.maxTemperature,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview
@Composable
private fun ForecastItemPreview() {
    WeatherAppTheme {
        ForecastItem(
            isSelected = true,
            id = 1,
            forecastDataUi = ForecastDataUi(
                dt = 1,
                title = "Today",
                icon = "https://openweathermap.org/img/wn/10d@2x.png",
                maxTemperature = "48",
                minTemperature = "56",
                progress = 1f
            )
        ) {}
    }
}
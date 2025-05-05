package com.jnasser.weather.presentation.weather_detail.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jnasser.core.presentation.designsystem.components.WeatherContentWithProgress
import com.jnasser.core.presentation.designsystem.theme.WeatherGrey

@Composable
fun DataWithProgress(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    colors: List<Color> = listOf(Color(0xFF6BBD2D), Color(0xFFCE2A2A)),
    value: Int,
    progress: Float
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                title,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = if (description != null) WeatherGrey else MaterialTheme.colorScheme.onSurface
                )
            )

            description?.let {
                Text(
                    description,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                )
            }
        }

        WeatherContentWithProgress(
            modifier = Modifier.size(45.dp),
            progress = progress,
            progressColors = colors
        ) {
            Text(
                text = value.toString(),
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal, fontSize = 10.sp)
            )
        }
    }
}
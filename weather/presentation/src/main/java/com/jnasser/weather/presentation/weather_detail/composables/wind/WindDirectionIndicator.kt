package com.jnasser.weather.presentation.weather_detail.composables.wind

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jnasser.core.presentation.designsystem.theme.WeatherGrey

@Composable
fun WindDirectionIndicator(
    modifier: Modifier = Modifier,
    direction: String
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .border(width = 2.dp, color = WeatherGrey, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = direction,
            style = MaterialTheme.typography.labelLarge.copy(
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}
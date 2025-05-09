package com.jnasser.weather.presentation.weather_detail.composables.uv

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme
import com.jnasser.core.presentation.designsystem.theme.WeatherGrey
import com.jnasser.weather.presentation.R
import com.jnasser.weather.presentation.weather_detail.composables.DataWithProgress
import com.jnasser.weather.presentation.weather_detail.model.UVDataUi

@Composable
fun UVContainer(
    modifier: Modifier = Modifier,
    uvDataUi: UVDataUi
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(25.dp))
            .background(Color.Transparent)
            .border(width = 2.dp, color = WeatherGrey, shape = RoundedCornerShape(25.dp))
    ) {
        DataWithProgress(
            title = stringResource(R.string.uv_index),
            value = uvDataUi.uvValue,
            progress = (uvDataUi.uvValue / 11f).coerceIn(0f, 1f)
        )
        Spacer(Modifier.height(10.dp))
        Text(
            modifier = Modifier.padding(horizontal = 15.dp),
            text = uvDataUi.state,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.SemiBold)
        )
        if (uvDataUi.preventUVHours.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(horizontal = 15.dp).padding(bottom = 15.dp),
                text = stringResource(
                    R.string.prevent_hours,
                    uvDataUi.preventUVHours.first(),
                    uvDataUi.preventUVHours.last()
                ),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}

@Preview
@Composable
private fun UVContainerPreview() {
    WeatherAppTheme {
        UVContainer(
            uvDataUi = UVDataUi(
                uvValue = 1,
                state = "Low",
                preventUVHours = listOf("12pm", "1pm", "2pm", "3pm")
            )
        )
    }
}
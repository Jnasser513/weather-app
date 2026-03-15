package com.jnasser.core.presentation.designsystem.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.LoadingIndicatorDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun WeatherAppLoading(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    size: Dp = 30.dp
) {
    LoadingIndicator(
        modifier = modifier.size(size),
        color = color
    )
}

@Preview
@Composable
private fun WeatherAppLoadingPreview() {
    WeatherAppTheme {
        WeatherAppLoading()
    }
}
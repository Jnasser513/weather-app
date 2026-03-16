package com.jnasser.core.presentation.designsystem.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun WeatherAppContainedLoading(
    modifier: Modifier = Modifier,
    size: Dp = 30.dp
) {
    ContainedLoadingIndicator(
        modifier = modifier.size(size),
        containerColor = LoadingIndicatorDefaults.containedIndicatorColor,
        indicatorColor = LoadingIndicatorDefaults.indicatorColor
    )
}

@Preview
@Composable
private fun WeatherAppContainedLoadingPreview() {
    WeatherAppTheme {
        WeatherAppContainedLoading()
    }
}
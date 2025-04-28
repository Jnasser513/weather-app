package com.jnasser.core.presentation.designsystem.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme

@Composable
fun WeatherAppScaffold(
    modifier: Modifier = Modifier,
    withGradient: Boolean = true,
    topApBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit = {}
) {
    Scaffold(
        topBar = topApBar,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = FabPosition.Center,
        modifier = modifier
    ) { padding ->
        if(withGradient) AnimatedGradientBackground() { content(padding) }
        else content(padding)
    }
}

@Preview
@Composable
private fun WeatherAppScaffoldPreview() {
    WeatherAppTheme {
        WeatherAppScaffold()
    }
}
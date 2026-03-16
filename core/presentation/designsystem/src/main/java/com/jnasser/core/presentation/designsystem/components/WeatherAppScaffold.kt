@file:OptIn(ExperimentalMaterial3Api::class)

package com.jnasser.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme

@Composable
fun WeatherAppScaffold(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    withGradient: Boolean = true,
    topApBar: @Composable () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null,
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit = {}
) {
    val finalModifier = scrollBehavior?.let {
        modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    } ?: modifier

    Scaffold(
        modifier = finalModifier,
        topBar = topApBar,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = FabPosition.Center
    ) { padding ->
        if(withGradient) AnimatedGradientBackground(isLoading = isLoading) { content(padding) }
        else {
            if(isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    WeatherAppContainedLoading(size = 50.dp)
                }
            }
            else content(padding)
        }
    }
}

@Preview
@Composable
private fun WeatherAppScaffoldPreview() {
    WeatherAppTheme {
        WeatherAppScaffold()
    }
}

@Preview
@Composable
private fun WeatherAppLoadingScaffoldPreview() {
    WeatherAppTheme {
        WeatherAppScaffold(isLoading = true)
    }
}
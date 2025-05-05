@file:OptIn(ExperimentalMaterial3Api::class)

package com.jnasser.core.presentation.designsystem.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme

@Composable
fun WeatherAppScaffold(
    modifier: Modifier = Modifier,
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
@file:OptIn(ExperimentalMaterial3Api::class)

package com.jnasser.core.presentation.designsystem.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jnasser.core.presentation.designsystem.R
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme

@Stable
data class WeatherAppFABConfig(
    val onclick: () -> Unit,
    val containerColor: Color? = null,
    val contentColor: Color? = null,
    val icon: ImageVector? = null
)

@Composable
fun WeatherAppFAB(
    modifier: Modifier = Modifier,
    config: WeatherAppFABConfig
) {
    FloatingActionButton(
        onClick = config.onclick,
        containerColor = config.containerColor ?: MaterialTheme.colorScheme.primary,
        contentColor = config.contentColor ?: MaterialTheme.colorScheme.secondary
    ) {
        Icon(
            imageVector = config.icon ?: Icons.Filled.Add,
            tint = config.contentColor ?: MaterialTheme.colorScheme.secondary,
            contentDescription = stringResource(R.string.content_fab)
        )
    }
}

@Preview
@Composable
private fun WeatherAppFABPreview() {
    WeatherAppTheme {
        WeatherAppScaffold(

            floatingActionButton = {
                WeatherAppFAB(
                    config = WeatherAppFABConfig(
                        onclick = {}
                    )
                )
            }
        ) {  }
    }
}
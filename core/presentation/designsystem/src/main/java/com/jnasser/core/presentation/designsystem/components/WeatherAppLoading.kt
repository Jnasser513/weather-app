package com.jnasser.core.presentation.designsystem.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme

@Composable
fun WeatherAppLoading(
    modifier: Modifier = Modifier
) {
   CircularProgressIndicator(
       modifier = modifier
           .size(30.dp),
       color = MaterialTheme.colorScheme.onSurface,
       strokeWidth = 2.dp
   )
}

@Preview
@Composable
private fun WeatherAppLoadingPreview() {
    WeatherAppTheme {
        WeatherAppLoading()
    }
}
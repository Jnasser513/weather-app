@file:OptIn(ExperimentalMaterial3Api::class)

package com.jnasser.core.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jnasser.core.presentation.designsystem.R
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme
import com.jnasser.core.presentation.designsystem.theme.WeatherWhite

/**
 * Configuration class for PokedexSearchBar.
 *
 * @param placeholder Resource ID for the hint text.
 * @param trailingIcon Optional trailing icon to display (e.g., search icon).
 * @param onSearch Callback triggered on every text change.
 */
data class WeatherAppSearchBarConfig(
    val placeholder: Int = R.string.search,
    val trailingIcon: ImageVector? = null,
    val onSearch: (String) -> Unit
)

/**
 * Custom search bar component for the Pokedex.
 * Uses BasicTextField to allow more control over internal padding and layout styling.
 *
 * @param modifier Optional layout modifier.
 * @param textFieldState State holder for the input text.
 * @param config Configuration options including placeholder, trailing icon, and search callback.
 */
@Composable
fun WeatherAppSearchBar(
    modifier: Modifier = Modifier,
    textFieldState: TextFieldState,
    config: WeatherAppSearchBarConfig
) {
    val text = textFieldState.text.toString()

    BasicTextField(
        value = text,
        onValueChange = {
            textFieldState.edit {
                replace(0, length, it)
            }
            config.onSearch(it)
        },
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = WeatherWhite,
            fontWeight = FontWeight.Normal
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(46.dp)
            .border(
                width = 1.dp,
                color = WeatherWhite,
                shape = RoundedCornerShape(25.dp)
            )
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(25.dp)
            ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    if (text.isEmpty()) {
                        Text(
                            text = stringResource(config.placeholder),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = WeatherWhite,
                                fontWeight = FontWeight.Normal
                            ),
                            color = WeatherWhite
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}

@Preview
@Composable
private fun WeatherAppSearchBarPreview() {
    WeatherAppTheme {
        WeatherAppSearchBar(
            textFieldState = rememberTextFieldState(),
            config = WeatherAppSearchBarConfig(
                onSearch = {}
            )
        )
    }
}

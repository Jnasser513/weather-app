@file:OptIn(ExperimentalMaterial3Api::class)

package com.jnasser.core.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme

data class WeatherTopAppBarConfig(
    val title: String,
    val navigationIcon: WeatherTopAppBar.NavigationIcon?,
    val actions: List<WeatherTopAppBar.Action> = emptyList(),
    val centerTitle: Boolean = false,
    val color: Color? = null,
    val scrollBehavior: TopAppBarScrollBehavior? = null
)

object WeatherTopAppBar {

    enum class ActionDisplay {
        ALWAYS, IF_ROOM, OVERFLOW, TEXT
    }

    data class Action(
        val text: String,
        val icon: ImageVector? = null,
        val enabled: Boolean = true,
        val display: ActionDisplay = ActionDisplay.IF_ROOM,
        val tintColor: Color? = null,
        val onClick: () -> Unit
    )

    sealed class NavigationIcon(
        val iconVector: ImageVector = Icons.Outlined.Home,
        val onClick: () -> Unit
    ) {
        data object None : NavigationIcon(onClick = {})

        data class Custom(
            val icon: ImageVector,
            val click: () -> Unit
        ) : NavigationIcon(
            iconVector = icon,
            onClick = click
        )

        data class Up(val click: () -> Unit): NavigationIcon(
            iconVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
            onClick = click
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherTopAppBar(
    config: WeatherTopAppBarConfig
) {
    TopAppBar(
        modifier = Modifier.padding(horizontal = 15.dp),
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = config.title,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                textAlign = if (config.centerTitle) TextAlign.Center else null
            )
        },colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent
        ),
        navigationIcon = {
            when (config.navigationIcon) {
                WeatherTopAppBar.NavigationIcon.None -> {}
                else -> {
                    config.navigationIcon?.let {
                        IconButton(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(40.dp)
                                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)),
                            onClick = it.onClick
                        ) {
                            Icon(
                                imageVector = it.iconVector,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        },
        actions = {
            config.actions.filter { it.icon != null }.forEach { action ->
                IconButton(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(40.dp)
                        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)),
                    onClick = action.onClick,
                    enabled = action.enabled
                ) {
                    Icon(
                        imageVector = action.icon ?: Icons.AutoMirrored.Default.KeyboardArrowLeft,
                        contentDescription = null,
                        tint = action.tintColor ?: LocalContentColor.current
                    )
                }
            }

            config.actions.filter {
                it.icon == null &&
                        it.display == WeatherTopAppBar.ActionDisplay.TEXT
            }.forEach { action ->
                TextButton(
                    onClick = action.onClick,
                    enabled = action.enabled
                ) {
                    Text(
                        text = action.text,
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = action.tintColor ?: LocalContentColor.current
                        )
                    )
                }
            }
        },
        scrollBehavior = config.scrollBehavior
    )
}

@Preview
@Composable
private fun WeatherTopAppBarPreview() {
    WeatherAppTheme {
        WeatherTopAppBar(
            config = WeatherTopAppBarConfig(
                title = "San Francisco, CA",
                centerTitle = true,
                navigationIcon = WeatherTopAppBar.NavigationIcon.Custom(
                    icon = Icons.Outlined.Home,
                    click = {}
                ),
                actions = listOf(
                    WeatherTopAppBar.Action(
                        text = "Map",
                        icon = com.jnasser.core.presentation.designsystem.theme.Icons.Map,
                        onClick = {}
                    )
                )
            )
        )
    }
}
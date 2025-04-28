package com.jnasser.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme
import com.jnasser.core.presentation.designsystem.util.meshGradient

@Composable
fun AnimatedGradientBackground(
    hasToolBar: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    val points = listOf(
        // 1
        listOf(
            Offset(0f, 0f) to Color(0xFF080714),
            Offset(0.2f, 0f) to Color(0xFF080714),
            Offset(0.4f, 0f) to Color(0xFF07080F),
            Offset(0.6f, 0f) to Color(0xFF090813),
            Offset(0.8f, 0f) to Color(0xFF160E19),
            Offset(1f, 0f) to Color(0xFF19101A)
        ),
        // 2
        listOf(
            Offset(0f, 0.2f) to Color(0xFF090910),
            Offset(0.2f, 0.2f) to Color(0xFF090816),
            Offset(0.4f, 0.2f) to Color(0xFF080816),
            Offset(0.6f, 0.2f) to Color(0xFF0B0816),
            Offset(0.8f, 0.2f) to Color(0xFF190E22),
            Offset(1f, 0.2f) to Color(0xFF221525)
        ),
        // 3
        listOf(
            Offset(0f, 0.4f) to Color(0xFF060710),
            Offset(0.2f, 0.4f) to Color(0xFF080715),
            Offset(0.4f, 0.4f) to Color(0xFF09081A),
            Offset(0.6f, 0.4f) to Color(0xFF100E2C),
            Offset(0.8f, 0.4f) to Color(0xFF38224B),
            Offset(1f, 0.4f) to Color(0xFF502F4C)
        ),
        // 4
        listOf(
            Offset(0f, 0.6f) to Color(0xFF0A091E),
            Offset(0.2f, 0.6f) to Color(0xFF090C30),
            Offset(0.4f, 0.6f) to Color(0xFF0E133B),
            Offset(0.6f, 0.6f) to Color(0xFF2C2557),
            Offset(0.8f, 0.6f) to Color(0xFF5B3962),
            Offset(1f, 0.6f) to Color(0xFF64485D)
        ),
        // 5
        listOf(
            Offset(0f, 0.8f) to Color(0xFF050810),
            Offset(0.2f, 0.8f) to Color(0xFF0A0D2F),
            Offset(0.4f, 0.8f) to Color(0xFF0E1437),
            Offset(0.6f, 0.8f) to Color(0xFF182044),
            Offset(0.8f, 0.8f) to Color(0xFF242C57),
            Offset(1f, 0.8f) to Color(0xFF31375A)
        ),
        // 6
        listOf(
            Offset(0f, 1f) to Color(0xFF090920),
            Offset(0.2f, 1f) to Color(0xFF0B0C1C),
            Offset(0.4f, 1f) to Color(0xFF0B0B23),
            Offset(0.6f, 1f) to Color(0xFF0C0E28),
            Offset(0.8f, 1f) to Color(0xFF131634),
            Offset(1f, 1f) to Color(0xFF171D42)
        )
    )


    Box(
        modifier = Modifier
            .fillMaxSize()
            .meshGradient(
                points = points,
                resolutionX = 32,
                resolutionY = 32,
                showPoints = false
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            content()
        }
    }

}


@Preview
@Composable
private fun AnimatedGradientBackgroundPreview() {
    WeatherAppTheme {
        AnimatedGradientBackground {

        }
    }
}
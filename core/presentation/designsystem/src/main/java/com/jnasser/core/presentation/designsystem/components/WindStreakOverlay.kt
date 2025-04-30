package com.jnasser.core.presentation.designsystem.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.fontscaling.MathUtils.lerp
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme

@Composable
fun WindFieldOverlay(
    modifier: Modifier = Modifier,
    streakCount: Int = 100,
    targetPoint: Offset = Offset(0.5f, 0.5f) // Centro relativo
) {
    val infiniteTransition = rememberInfiniteTransition(label = "windField")
    val color = MaterialTheme.colorScheme.onSurface.copy(0.2f)

    // Genera los datos una vez
    val streaks = remember {
        List(streakCount) {
            WindStreak(
                start = Offset(
                    x = (0..1000).random() / 1000f,
                    y = (0..1000).random() / 1000f
                ),
                speed = (5000..10000).random()
            )
        }
    }

    // Calcula las animaciones de desplazamiento para cada streak
    val animatedValues = streaks.mapIndexed { index, streak ->
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = streak.speed, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            ),
            label = "streak_anim_$index"
        ).value
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val center = Offset(canvasWidth * targetPoint.x, canvasHeight * targetPoint.y)

        streaks.forEachIndexed { index, streak ->
            val progress = animatedValues[index]

            val start = Offset(
                x = canvasWidth * streak.start.x,
                y = canvasHeight * streak.start.y
            )

            val current = Offset(
                x = lerp(start.x, center.x, progress),
                y = lerp(start.y, center.y, progress)
            )

            val path = Path().apply {
                moveTo(start.x, start.y)
                quadraticTo(
                    (start.x + center.x) / 2,
                    (start.y + center.y) / 2 + 20f,
                    current.x,
                    current.y
                )
            }

            drawPath(
                path,
                color = color,
                style = Stroke(width = 3f, cap = StrokeCap.Round)
            )
        }
    }
}

data class WindStreak(val start: Offset, val speed: Int)


@Preview
@Composable
private fun WindStreakOverlayPreview() {
    WeatherAppTheme {
        WindFieldOverlay()
    }
}
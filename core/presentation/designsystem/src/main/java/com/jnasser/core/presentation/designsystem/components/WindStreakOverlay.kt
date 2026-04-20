package com.jnasser.core.presentation.designsystem.components

import androidx.compose.animation.core.LinearEasing
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import com.jnasser.core.presentation.designsystem.components.animations.WeatherMotionTokens
import com.jnasser.core.presentation.designsystem.components.animations.rememberWeatherMotionSettings
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme
import kotlin.math.absoluteValue

@Composable
fun WindFieldOverlay(
    modifier: Modifier = Modifier,
    streakCount: Int = 40,
    targetPoint: Offset = Offset(0.5f, 0.5f) // Centro relativo
) {
    val motion = rememberWeatherMotionSettings()
    val infiniteTransition = rememberInfiniteTransition(label = "windField")
    val color = MaterialTheme.colorScheme.onSurface.copy(0.2f)

    val streaks = remember(streakCount) {
        List(streakCount) {
            WindStreak(
                start = Offset(
                    x = (0..1000).random() / 1000f,
                    y = (0..1000).random() / 1000f
                ),
                phaseOffset = (0..1000).random() / 1000f,
                alpha = (20..90).random() / 100f,
                trailLength = (12..24).random() / 100f,
                strokeWidth = (1..2).random().toFloat()
            )
        }
    }

    val phase = if (motion.animationsEnabled) {
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = motion.durationMillis(WeatherMotionTokens.WindLoop),
                    easing = LinearEasing
                )
            ),
            label = "windFieldPhase"
        ).value
    } else {
        1f
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        if (!motion.animationsEnabled) return@Canvas

        val canvasWidth = size.width
        val canvasHeight = size.height
        val center = Offset(canvasWidth * targetPoint.x, canvasHeight * targetPoint.y)

        streaks.forEachIndexed { index, streak ->
            val progress = (phase + streak.phaseOffset) % 1f

            val start = Offset(
                x = canvasWidth * streak.start.x,
                y = canvasHeight * streak.start.y
            )

            val startLerp = (progress - streak.trailLength).coerceIn(0f, 1f)
            val endLerp = progress.coerceIn(0f, 1f)

            val currentStart = start.lerpTo(center, startLerp)
            val currentEnd = start.lerpTo(center, endLerp)
            val alpha = streak.alpha * (1f - (0.5f - progress).absoluteValue)

            drawLine(
                color = color.copy(alpha = alpha.coerceIn(0.06f, 0.22f)),
                start = currentStart,
                end = currentEnd,
                strokeWidth = streak.strokeWidth,
                cap = StrokeCap.Round
            )
        }
    }
}

private fun Offset.lerpTo(target: Offset, fraction: Float): Offset = Offset(
    x = x + ((target.x - x) * fraction),
    y = y + ((target.y - y) * fraction)
)

data class WindStreak(
    val start: Offset,
    val phaseOffset: Float,
    val alpha: Float,
    val trailLength: Float,
    val strokeWidth: Float
)

@Preview
@Composable
private fun WindStreakOverlayPreview() {
    WeatherAppTheme {
        WindFieldOverlay()
    }
}

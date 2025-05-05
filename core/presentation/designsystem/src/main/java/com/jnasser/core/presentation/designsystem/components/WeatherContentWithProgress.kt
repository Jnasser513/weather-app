package com.jnasser.core.presentation.designsystem.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.jnasser.core.presentation.designsystem.theme.WeatherGrey
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun WeatherContentWithProgress(
    modifier: Modifier = Modifier,
    progress: Float, // e.g., 0.8f
    progressColors: List<Color>,
    content: @Composable () -> Unit
) {
    val animatedProgress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        animatedProgress.snapTo(0f)
        animatedProgress.animateTo(
            targetValue = progress,
            animationSpec = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        )
    }

    Box(
        modifier = modifier.size(60.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val strokeWidth = 4.dp.toPx()
            val radius = size.minDimension / 2
            val center = Offset(size.width / 2, size.height / 2)

            val startAngle = -225f
            val sweepAngle = 270f

            val startRadians = Math.toRadians(startAngle.toDouble())
            val endRadians = Math.toRadians((startAngle + sweepAngle).toDouble())

            drawArc(
                color = WeatherGrey,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )

            drawArc(
                brush = Brush.linearGradient(
                    colors = progressColors,
                    start = polarToCartesian(center, radius, startRadians),
                    end = polarToCartesian(center, radius, endRadians)
                ),
                startAngle = startAngle,
                sweepAngle = sweepAngle * animatedProgress.value,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
        }

        content()
    }
}


fun polarToCartesian(center: Offset, radius: Float, angleRad: Double): Offset {
    return Offset(
        (center.x + cos(angleRad) * radius).toFloat(),
        (center.y + sin(angleRad) * radius).toFloat()
    )
}
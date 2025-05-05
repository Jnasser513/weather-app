package com.jnasser.core.presentation.designsystem.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
    progress: Float,
    progressColors: List<Color>,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier.size(60.dp),
        contentAlignment = Alignment.Center
    ) {
        val progressAnimated by animateFloatAsState(
            targetValue = progress
        )

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
                style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
            )
            val startOffset = polarToCartesian(center, radius, startRadians)
            val endOffset = polarToCartesian(center, radius, endRadians)

            drawArc(
                brush = Brush.linearGradient(
                    colors = progressColors,
                    start = startOffset,
                    end = endOffset
                ),
                startAngle = startAngle,
                sweepAngle = sweepAngle * progressAnimated,
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
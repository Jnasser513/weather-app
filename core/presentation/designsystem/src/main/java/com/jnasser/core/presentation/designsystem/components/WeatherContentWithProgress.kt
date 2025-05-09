package com.jnasser.core.presentation.designsystem.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jnasser.core.presentation.designsystem.theme.WeatherAppTheme
import com.jnasser.core.presentation.designsystem.theme.WeatherGrey
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun WeatherContentWithProgress(
    modifier: Modifier = Modifier,
    id: Int? = null,
    progress: Float,
    progressColors: List<Color>,
    content: @Composable () -> Unit
) {
    val animatedProgress = remember { Animatable(0f) }
    var hasAnimated by rememberSaveable(id) { mutableStateOf(false) }

    LaunchedEffect(id) {
        if (!hasAnimated) {
            animatedProgress.snapTo(0f)
            animatedProgress.animateTo(
                targetValue = progress,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = FastOutSlowInEasing
                )
            )
            hasAnimated = true
        } else {
            animatedProgress.snapTo(progress)
        }
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

@Preview
@Composable
private fun WeatherContentWithProgressPreview() {
    WeatherAppTheme {
        WeatherContentWithProgress(
            modifier = Modifier.size(45.dp),
            id = 1,
            progress = 0.9f,
            progressColors = listOf(Color(0xFF6BBD2D), Color(0xFFCE2A2A))
        ) {
            Text(
                text = "70",
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal, fontSize = 10.sp)
            )
        }
    }

}
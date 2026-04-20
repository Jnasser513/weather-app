package com.jnasser.core.presentation.designsystem.components.animations

import android.animation.ValueAnimator
import android.content.Context
import android.os.Build
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import kotlin.math.roundToInt

object WeatherMotionTokens {
    const val Short = 180
    const val Medium = 280
    const val Long = 420
    const val Extended = 560
    const val Stagger = 140
    const val WindLoop = 4800
}

data class WeatherMotionSettings(
    val durationScale: Float
) {
    val animationsEnabled: Boolean
        get() = durationScale > 0f

    fun durationMillis(baseMillis: Int): Int {
        if (!animationsEnabled) return 0

        return (baseMillis * durationScale)
            .roundToInt()
            .coerceAtLeast(1)
    }

    fun staggerMillis(baseMillis: Int): Long = durationMillis(baseMillis).toLong()
}

@Composable
fun rememberWeatherMotionSettings(): WeatherMotionSettings {
    val context = LocalContext.current

    return remember(context) {
        WeatherMotionSettings(
            durationScale = context.readAnimatorDurationScale()
        )
    }
}

private fun Context.readAnimatorDurationScale(): Float {
    val durationScale = runCatching {
        Settings.Global.getFloat(
            contentResolver,
            Settings.Global.ANIMATOR_DURATION_SCALE
        )
    }.getOrDefault(1f)

    val animationsEnabled = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        ValueAnimator.areAnimatorsEnabled()
    } else {
        durationScale > 0f
    }

    return if (animationsEnabled) durationScale.coerceAtLeast(0f) else 0f
}

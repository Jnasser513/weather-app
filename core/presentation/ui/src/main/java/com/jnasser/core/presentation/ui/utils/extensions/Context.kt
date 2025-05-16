package com.jnasser.core.presentation.ui.utils.extensions

import android.content.Context
import com.jnasser.core.presentation.ui.R
import kotlin.math.roundToInt

fun Context.getLocalizedWindDescription(windSpeed: Float?): String {
    return windSpeed?.let {
        when {
            it < 1 -> getString(R.string.wind_description_calm)
            it < 4 -> getString(R.string.wind_description_light_air)
            it < 8 -> getString(R.string.wind_description_light_breeze)
            it < 13 -> getString(R.string.wind_description_gentle_breeze)
            it < 19 -> getString(R.string.wind_description_moderate_breeze)
            it < 25 -> getString(R.string.wind_description_fresh_breeze)
            it < 32 -> getString(R.string.wind_description_strong_breeze)
            it < 39 -> getString(R.string.wind_description_near_gale)
            it < 47 -> getString(R.string.wind_description_gale)
            it < 55 -> getString(R.string.wind_description_strong_gale)
            it < 64 -> getString(R.string.wind_description_whole_gale)
            it < 73 -> getString(R.string.wind_description_storm)
            else -> getString(R.string.wind_description_hurricane_force)
        }
    } ?: getString(R.string.wind_no_info)
}

fun Context.getWindDirectionFromDegrees(windDeg: Int?): String {
    return windDeg?.let {
        val directions = resources.getStringArray(R.array.wind_directions)
        val index = ((windDeg % 360) / 22.5).roundToInt() % 16
        directions[index]
    } ?: getString(R.string.wind_direction_no_info)
}
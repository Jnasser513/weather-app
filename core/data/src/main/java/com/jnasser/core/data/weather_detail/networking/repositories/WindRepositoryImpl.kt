package com.jnasser.core.data.weather_detail.networking.repositories

import android.content.Context
import com.jnasser.core.data.R
import com.jnasser.core.domain.repositories.WindRepository

class WindRepositoryImpl(
    private val context: Context
): WindRepository {

    override fun getLocalizedWindDescription(windSpeed: Double): String {
        return when {
            windSpeed < 1 -> context.getString(R.string.wind_description_calm)
            windSpeed < 4 -> context.getString(R.string.wind_description_light_air)
            windSpeed < 8 -> context.getString(R.string.wind_description_light_breeze)
            windSpeed < 13 -> context.getString(R.string.wind_description_gentle_breeze)
            windSpeed < 19 -> context.getString(R.string.wind_description_moderate_breeze)
            windSpeed < 25 -> context.getString(R.string.wind_description_fresh_breeze)
            windSpeed < 32 -> context.getString(R.string.wind_description_strong_breeze)
            windSpeed < 39 -> context.getString(R.string.wind_description_near_gale)
            windSpeed < 47 -> context.getString(R.string.wind_description_gale)
            windSpeed < 55 -> context.getString(R.string.wind_description_strong_gale)
            windSpeed < 64 -> context.getString(R.string.wind_description_whole_gale)
            windSpeed < 73 -> context.getString(R.string.wind_description_storm)
            else -> context.getString(R.string.wind_description_hurricane_force)
        }
    }
}
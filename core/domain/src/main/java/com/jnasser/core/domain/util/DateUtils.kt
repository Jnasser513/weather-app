package com.jnasser.core.domain.util

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.TextStyle
import java.util.Locale

object DateUtils {

    fun getDayNameFromTimeStamp(timeStampSeconds: Long): String {
        val date = Instant.ofEpochSecond(timeStampSeconds)
            .atZone(ZoneId.systemDefault())
            .dayOfWeek

        return date.getDisplayName(TextStyle.SHORT_STANDALONE, Locale.getDefault())
    }

    fun isToday(timeStampSeconds: Long): Boolean {
        val date = Instant.ofEpochSecond(timeStampSeconds)
            .atZone(ZoneId.systemDefault()).toLocalDate()
        val today = LocalDate.now()

        return date == today
    }
}
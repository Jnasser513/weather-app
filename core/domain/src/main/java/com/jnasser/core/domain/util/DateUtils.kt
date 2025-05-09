package com.jnasser.core.domain.util

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.TextStyle
import java.util.Locale

object DateUtils {

    fun getDayNameFromTimeStamp(timeStampMillis: Long): String {
        val date = Instant.ofEpochMilli(timeStampMillis)
            .atZone(ZoneId.systemDefault())
            .dayOfWeek

        return date.getDisplayName(TextStyle.FULL, Locale.getDefault())
    }

    fun isToday(timeStampMillis: Long): Boolean {
        val date = Instant.ofEpochMilli(timeStampMillis)
            .atZone(ZoneId.systemDefault()).toLocalDate()
        val today = LocalDate.now()

        return date == today
    }
}
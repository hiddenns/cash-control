package com.cashcontrol.feature.util

import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun getDefaultDateFormat() = "yyyy/MM/dd HH:mm:ss"

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun getCurrentDateTime(): Date {
    return Calendar.getInstance().time
}

fun getCurrentTimeUtc() = OffsetDateTime.now(ZoneOffset.UTC).toEpochSecond()
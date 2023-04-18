package com.example.weatheralertapp

import java.time.Duration
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.random.Random

fun generateRandom(max: Int) = Random.nextInt(max)

fun getFormattedDate(timeStamp: String?): String {
    timeStamp ?: return "unknown"

    val dtfForInputString = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.ENGLISH)
    val odt: OffsetDateTime = OffsetDateTime.parse(timeStamp, dtfForInputString)
    return odt.dayOfMonth.toString() + " " + odt.month + " " + odt.year.toString() + ", " + odt.hour + ":" + odt.minute
}

fun calculateDuration(startTimeStamp: String, endTimeStamp: String?): String {
    endTimeStamp ?: return "unknown"
    val dtfForInputString =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.ENGLISH)

    val start: LocalTime = LocalTime.parse(startTimeStamp, dtfForInputString)
    val end: LocalTime = LocalTime.parse(endTimeStamp, dtfForInputString)

    val diff: Duration = Duration.between(start, end)

    val hours: Long = diff.toHours()
    val minutes: Long = diff.minusHours(hours).toMinutes()
    return String.format("%02d:%02d", hours, minutes)
}
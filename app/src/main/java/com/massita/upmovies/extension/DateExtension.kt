package com.massita.upmovies.extension

import java.text.DateFormat
import java.util.*

fun Date.toLocalDateString() : String {
    val df = DateFormat.getDateInstance(DateFormat.DATE_FIELD, Locale.getDefault())

    return df.format(this)
}

fun Date.getDaysAgo(daysAgo : Int) : Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)

    return calendar.time
}

fun Date.isAfterToday() : Boolean {
    val calendar = Calendar.getInstance()
    return after(calendar.time)
}

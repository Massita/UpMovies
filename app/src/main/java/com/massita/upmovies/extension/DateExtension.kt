package com.massita.upmovies.extension

import java.text.DateFormat
import java.util.*

fun Date.toLocalDateString() : String {
    val df = DateFormat.getDateInstance(DateFormat.DATE_FIELD, Locale.getDefault())

    return df.format(this)
}
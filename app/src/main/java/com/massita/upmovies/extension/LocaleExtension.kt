package com.massita.upmovies.extension

import java.util.*

fun Locale.getDefaultIsoString() : String {
    var localeString = toString().replace('_', '-')

    return localeString
}
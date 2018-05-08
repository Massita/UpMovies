package com.massita.upmovies.helper

import android.content.Context
import android.content.ContextWrapper
import com.google.gson.Gson
import com.massita.upmovies.feature.detail.notification.ScheduledAlarm

class SharedPreferencesHelper(val context: Context) : ContextWrapper(context) {

    companion object {
        const val SHARED_PREFERENCES_NAME = "UPVIDEOS_PREF"
        const val SCHEDULE_ALARM_KEY = "SCHEDULE_ALARM_KEY"
    }

    fun saveScheduledAlarm(value: ScheduledAlarm) {
        val settings = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0)
        val editor = settings.edit()
        editor.putString(SCHEDULE_ALARM_KEY, Gson().toJson(value))
        editor.apply()
    }

}
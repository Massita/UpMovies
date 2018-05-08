package com.massita.upmovies.helper

import android.content.Context
import android.content.ContextWrapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.massita.upmovies.feature.detail.notification.ScheduledAlarm

class SharedPreferencesHelper(val context: Context) : ContextWrapper(context) {

    companion object {
        const val SHARED_PREFERENCES_NAME = "UPVIDEOS_PREF"
        const val SCHEDULE_ALARM_KEY = "SCHEDULE_ALARM_KEY"
    }

    fun saveScheduledAlarm(value: List<ScheduledAlarm>) {
        val settings = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0)
        val editor = settings.edit()
        editor.putString(SCHEDULE_ALARM_KEY, Gson().toJson(value))
        editor.apply()
    }

    fun addScheduledAlarm(value: ScheduledAlarm) {
        val alarms = mutableListOf<ScheduledAlarm>()
        alarms.addAll(getScheduledAlarms())
        alarms.add(value)

        saveScheduledAlarm(alarms)
    }

    fun getScheduledAlarms() : List<ScheduledAlarm> {
        val settings = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0)
        val resultString = settings.getString(SCHEDULE_ALARM_KEY, "")

        if(resultString.isNotBlank()) {
            val type = object : TypeToken<List<ScheduledAlarm>>() {}.type
            return Gson().fromJson(resultString, type)
        }

        return arrayListOf()
    }

}
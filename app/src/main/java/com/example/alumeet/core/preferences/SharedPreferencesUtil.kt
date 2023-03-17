package com.example.alumeet.core.preferences

import android.content.Context
import com.example.alumeet.App
import com.example.alumeet.BuildConfig
import com.example.alumeet.utils.constants.Constants.ACCESS_TOKEN
import com.example.alumeet.utils.constants.Constants.LOGGED_IN

object SharedPreferencesUtil {
    private val sharedPreference = App.context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)

    var accessToken: String
        get() = getStringKey(ACCESS_TOKEN)
        set(value) = setStringKey(ACCESS_TOKEN, value)

    var loggedIn: String
        get() = getStringKey(LOGGED_IN, "")
        set(value) = setStringKey(LOGGED_IN, value)

    fun getStringKey(key: String, defaultValue: String = ""): String = sharedPreference.getString(key, defaultValue)!!
    fun setStringKey(key: String, value: String) {
        sharedPreference.edit().putString(key, value).apply()
    }

    fun clearSharedPreferences() {
        sharedPreference.edit().clear().apply()
    }
}
package com.example.catsocial.data.datastore

import android.content.Context

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("session_prefs", Context.MODE_PRIVATE)



    fun saveLoginState(isLoggedIn: Boolean) {
        prefs.edit().putBoolean("is_logged_in", isLoggedIn).apply()
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean("is_logged_in", false)
    }
}
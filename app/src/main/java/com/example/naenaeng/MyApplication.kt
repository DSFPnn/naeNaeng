package com.example.naenaeng

import android.app.Application

class MyApplication : Application() {
    companion object {
        lateinit var prefs: MysharedPreferences
    }

    override fun onCreate() {
        prefs = MysharedPreferences(applicationContext)
        super.onCreate()
    }
}
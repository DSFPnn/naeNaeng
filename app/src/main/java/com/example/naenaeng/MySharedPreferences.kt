package com.example.naenaeng

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class MySharedPreferences(context : Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    fun readAll(){ // 저장되어 있는 데이터 Log로 출력
        Log.d("prefs readAll", prefs.all.toString())
    }

    fun getString(key: String, defValue: String): String { // 데이터 불러오기
        return prefs.getString(key, defValue).toString()
    }

    fun setString(key: String, str: String) { // 데이터 저장장
       prefs.edit().putString(key, str).apply()
    }
}
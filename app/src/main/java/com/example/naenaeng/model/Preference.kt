package com.example.naenaeng.model

data class Preference (
    val country : ArrayList<String> = ArrayList(),
    val taste : ArrayList<String> = ArrayList(),
    val cook : ArrayList<String> = ArrayList(),
    val allergy : ArrayList<String> = ArrayList()
)
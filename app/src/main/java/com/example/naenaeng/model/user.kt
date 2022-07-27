package com.example.naenaeng.model

data class User (
    val allergy: ArrayList<String>? = null,
    val email: String? = null,
    val ingredients: ArrayList<HashMap<String,String>>? = null
)

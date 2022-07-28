package com.example.naenaeng.model

data class User (
    val allergy: ArrayList<String> = ArrayList(),
    val email: String = "",
    val ingredients: ArrayList<Ingredient> = ArrayList()
)

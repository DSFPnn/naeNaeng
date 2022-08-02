package com.example.naenaeng.model

data class User (
    val allergy: ArrayList<String> = ArrayList(),
    val email: String = "",
    val name: String = "",
    val ingredients: ArrayList<Ingredient> = ArrayList(),
    val preference: Preference = Preference()
)

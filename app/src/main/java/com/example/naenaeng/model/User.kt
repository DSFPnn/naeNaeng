package com.example.naenaeng.model

data class User (
    val allergy: ArrayList<String> = ArrayList(),
    val email: String = "",
    val name: String = "",
    var ingredients: List<Ingredient> = listOf(),
    val preference: Preference = Preference()
)

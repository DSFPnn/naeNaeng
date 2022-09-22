package com.example.naenaeng.model

import com.google.firebase.firestore.PropertyName

data class Menu (
        val name:String = "",
        val title:String = "",
        val index:String = "",
        val ingredients:ArrayList<String> = ArrayList(),
        @get:PropertyName("additional ingredients") @set:PropertyName("additional ingredients")
        var additionalIngredients:String = "",
        @get:PropertyName("essential ingredients") @set:PropertyName("essential ingredients")
        var essentialIngredients:String = "",
        val imageName:String = "",
        val step:String = "",
        val filter:ArrayList<String> = ArrayList(),
        var imageInt:Int = 0
)
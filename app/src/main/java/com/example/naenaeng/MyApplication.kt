package com.example.naenaeng

import android.app.Application
import android.util.Log

class MyApplication : Application() {
    companion object {
        lateinit var prefs: MySharedPreferences
        lateinit var ingredientIamgeHash:HashMap<String,Int>
        lateinit var recipeImageHash:HashMap<String,Int>
    }

    override fun onCreate() {
        prefs = MySharedPreferences(applicationContext)
        super.onCreate()

        // 재료 사진 배열
        // bean, egg, fruit, meat, milk, mushroom, nut, sauce, seafood, tofu, vegetables
        ingredientIamgeHash = HashMap()
        ingredientIamgeHash["fruit"] = R.drawable.fruit
        ingredientIamgeHash["bean"] = R.drawable.bean
        ingredientIamgeHash["egg"] = R.drawable.egg
        ingredientIamgeHash["grain"] = R.drawable.grain
        ingredientIamgeHash["milk"] = R.drawable.milk
        ingredientIamgeHash["nut"] = R.drawable.nut
        ingredientIamgeHash["paste"] = R.drawable.paste
        ingredientIamgeHash["vegetables"] = R.drawable.vegetables
        ingredientIamgeHash["meat"] = R.drawable.meat
        ingredientIamgeHash["mushrooms"] = R.drawable.mushrooms
        ingredientIamgeHash["seafoods"] = R.drawable.seafoods
        ingredientIamgeHash["tofus"] = R.drawable.tofus

        // 레시피 사진 배열
        recipeImageHash = HashMap()
        recipeImageHash["tomatoPasta"] = R.drawable.tomatopasta
        recipeImageHash["bibimbap"] = R.drawable.bibimbap
        recipeImageHash["kyudon"] = R.drawable.kyudon
        recipeImageHash["mapoTofu"] = R.drawable.mapotofu
        recipeImageHash["tteokbokki"] = R.drawable.tteokbokki
        recipeImageHash["chiliShrimp"] = R.drawable.chilishrimp
        recipeImageHash["energyBar"] = R.drawable.energybar
        recipeImageHash["fruitBowl"] = R.drawable.fruitbowl
        recipeImageHash["odenSoup"] = R.drawable.odensoup
        recipeImageHash["samgyetang"] = R.drawable.samgyetang
        recipeImageHash["vongolePasta"] = R.drawable.vongolepasta
        Log.d("hashhh_myappli", recipeImageHash.toString())

    }
}
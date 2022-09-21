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
        ingredientIamgeHash["bean"] = R.drawable.bean
        ingredientIamgeHash["egg"] = R.drawable.egg
        ingredientIamgeHash["fruit"] = R.drawable.fruit
        ingredientIamgeHash["meat"] = R.drawable.meat
        ingredientIamgeHash["milk"] = R.drawable.milk
        ingredientIamgeHash["mushroom"] = R.drawable.mushroom
        ingredientIamgeHash["nut"] = R.drawable.nut
        ingredientIamgeHash["sauce"] = R.drawable.sauce
        ingredientIamgeHash["seafood"] = R.drawable.seafood
        ingredientIamgeHash["tofu"] = R.drawable.tofu
        ingredientIamgeHash["vegetables"] = R.drawable.vegetables

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
package com.example.naenaeng.repository

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.naenaeng.MyApplication.Companion.prefs
import com.example.naenaeng.model.Ingredient
import com.example.naenaeng.model.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class UserRepository() {
    //DB에서 유저정보 가져오기
    fun getData(): LiveData<User> {
        val db = Firebase.firestore
        val mutableData = MutableLiveData<User>()
        val user = prefs.getString("email","null")
        var sortNum: String = "0"
        var comparator : Comparator<Ingredient> = compareBy { it.added }

//        db.collection("users/$user").orderBy("added").get()
//            .addOnSuccessListener { documentSnapshot ->
//                val data = documentSnapshot.toObject<User>()
//                mutableData.value=data!!
//                Log.d("ingred repo",data.toString())
//            }
//            .addOnFailureListener { exception ->
//                Log.d(ContentValues.TAG, "get failed with ", exception)
//            }

        db.collection("users").document(prefs.getString("email","null")).get()
            .addOnSuccessListener { documentSnapshot ->
                val data = documentSnapshot.toObject<User>()
                if (data != null) {
                    prefs.setString("pref",data.preference.toString())
                    prefs.setString("allergy",data.allergy.toString())
                    sortNum = prefs.getString("sortNum","0")
                    when(sortNum){
                        "0" -> comparator = compareBy { it.added }
                        "1" -> comparator = compareBy { it.name }
                        "2" -> comparator = compareBy { it.date }
                    }
                    val sortedIngredient = data.ingredients.sortedWith(comparator)
                    data.ingredients = sortedIngredient

                }
                mutableData.value=data!!
                Log.d("ingred repo",data.toString())
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }

        return mutableData
    }
}
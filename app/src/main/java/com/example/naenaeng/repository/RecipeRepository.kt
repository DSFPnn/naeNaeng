package com.example.naenaeng.repository

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.naenaeng.model.Recipe
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class RecipeRepository {
    // DB에서 레시피 정보 가져오기
    fun getData(): LiveData<Recipe> {
        val db = Firebase.firestore
        val mutableData = MutableLiveData<Recipe>()

        db.collection("public").document("recipe").get()
            .addOnSuccessListener { documentSnapshot ->
                val data = documentSnapshot.toObject<Recipe>()
                mutableData.value=data!!
                Log.d("recipee repo",data.toString())
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }
        return mutableData
    }
}
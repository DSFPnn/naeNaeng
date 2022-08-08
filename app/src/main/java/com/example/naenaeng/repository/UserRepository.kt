package com.example.naenaeng.repository

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.naenaeng.MyApplication.Companion.prefs
import com.example.naenaeng.model.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class UserRepository {
    //DB에서 유저정보 가져오기
    fun getData(): LiveData<User> {
        val db = Firebase.firestore
        val mutableData = MutableLiveData<User>()

        db.collection("users").document(prefs.getString("email","null")).get()
            .addOnSuccessListener { documentSnapshot ->
                val data = documentSnapshot.toObject<User>()
                if (data != null) {
                    prefs.setString("pref",data.preference.toString())

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
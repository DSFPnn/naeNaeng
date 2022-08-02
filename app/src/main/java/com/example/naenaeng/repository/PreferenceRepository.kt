package com.example.naenaeng.repository

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.naenaeng.model.Preference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class PreferenceRepository {
    //DB에서 알러지이름 가져오기
    fun getData(): LiveData<Preference> {
        val db = Firebase.firestore
        val mutableData = MutableLiveData<Preference>()

        db.collection("public").document("preference").get()
            .addOnSuccessListener { documentSnapshot ->
                val data = documentSnapshot.toObject<Preference>()
                mutableData.value=data!!
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }
        return mutableData
    }
}
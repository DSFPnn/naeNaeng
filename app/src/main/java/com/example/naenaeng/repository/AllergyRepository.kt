package com.example.naenaeng.repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.naenaeng.model.Allergy
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class AllergyRepository {
    //DB에서 알러지이름 가져오기
    fun getData(): LiveData<Allergy>{
        val db = Firebase.firestore
        val mutableData = MutableLiveData<Allergy>()

        db.collection("allergy").document("allergyName").get()
            .addOnSuccessListener { documentSnapshot ->
                val data = documentSnapshot.toObject<Allergy>()
                mutableData.value=data!!
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
        return mutableData
    }
}
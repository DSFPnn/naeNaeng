package com.example.naenaeng.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.naenaeng.model.Allergy
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AllergyRepository {

    fun getData(): LiveData<MutableList<Allergy>> {
        val mutableData = MutableLiveData<MutableList<Allergy>>()
        val database = Firebase.database
        val myRef = database.getReference("allergy")
        myRef.addValueEventListener(object : ValueEventListener {
            val listData: MutableList<Allergy> = mutableListOf<Allergy>()
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (allergySnapshot in snapshot.children) {
                        val getData = allergySnapshot.getValue(Allergy::class.java)
                        listData.add(getData!!)
                        mutableData.value = listData
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        Log.d("allergyy repo",mutableData.value.toString())
        return mutableData
    }
}
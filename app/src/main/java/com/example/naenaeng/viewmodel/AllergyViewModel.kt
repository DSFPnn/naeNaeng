package com.example.naenaeng.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.naenaeng.model.Allergy
import com.example.naenaeng.repository.AllergyRepository

class AllergyViewModel :ViewModel(){
    private val _allergyLiveData:MutableLiveData<MutableList<Allergy>>
        = MutableLiveData()
    val allergyLiveData:LiveData<MutableList<Allergy>>
        get() = _allergyLiveData
    private val repo = AllergyRepository()

    fun getAllergy() {
        Log.d("allergyy vm",_allergyLiveData.value.toString())
        repo.getData().observeForever{
            _allergyLiveData.value = it
            Log.d("allergyy vm",_allergyLiveData.value.toString())
        }
    }
}
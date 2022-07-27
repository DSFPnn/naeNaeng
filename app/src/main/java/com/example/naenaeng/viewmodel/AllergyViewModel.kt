package com.example.naenaeng.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.naenaeng.repository.AllergyRepository

class AllergyViewModel :ViewModel(){
    private val _allergyLiveData:MutableLiveData<ArrayList<String>>
        = MutableLiveData()
    val allergyLiveData:LiveData<ArrayList<String>>
        get() = _allergyLiveData
    private val repo = AllergyRepository()

    fun getAllergy() {
        Log.d("allergyy vm",_allergyLiveData.value.toString())
        repo.getData().observeForever{
            _allergyLiveData.postValue(it.names)
            Log.d("allergyy vm",_allergyLiveData.value.toString())
        }
    }
}
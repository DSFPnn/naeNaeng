package com.example.naenaeng.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.naenaeng.model.Preference
import com.example.naenaeng.repository.PreferenceRepository

class PreferenceViewModel : ViewModel(){
    private val _preferenceLiveData: MutableLiveData<Preference>
            = MutableLiveData()
    val preferenceLiveData: LiveData<Preference>
        get() = _preferenceLiveData
    private val repo = PreferenceRepository()

    fun getPreference() {
        Log.d("prefer vm",_preferenceLiveData.value.toString())
        repo.getData().observeForever{
            _preferenceLiveData.postValue(it)
            Log.d("prefer vm",_preferenceLiveData.value.toString())
        }
    }
}
package com.example.naenaeng.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.naenaeng.model.Ingredient
import com.example.naenaeng.model.Preference
import com.example.naenaeng.repository.UserRepository

class UserViewModel : ViewModel(){
    private val _userAllergyLiveData: MutableLiveData<ArrayList<String>>
            = MutableLiveData()
    val userAllergyLiveData: LiveData<ArrayList<String>>
        get() = _userAllergyLiveData
    private val repo = UserRepository()

    private val _userIngredientLiveData: MutableLiveData<List<Ingredient>>
            = MutableLiveData()
    val userIngredientLiveData: LiveData<List<Ingredient>>
        get() = _userIngredientLiveData

    private val _userPreferenceLiveData: MutableLiveData<Preference>
            = MutableLiveData()
    val userPreferenceLiveData: LiveData<Preference>
        get() = _userPreferenceLiveData

    fun getUserAllergy() {
        repo.getData().observeForever{
            _userAllergyLiveData.postValue(it.allergy)
            Log.d("allergyy vm",_userAllergyLiveData.value.toString())
        }
    }
    fun getUserIngredient() {
        repo.getData().observeForever{
            _userIngredientLiveData.value = it.ingredients
            // .sortWith(compareBy<Ingredient>{it.added}.thenBy{it.name})
            Log.d("ingred vm", _userIngredientLiveData.value.toString())
        }
    }
    fun getUserPreference(){
        repo.getData().observeForever{
            _userPreferenceLiveData.value = it.preference
            Log.d("ingred vm", _userPreferenceLiveData.value.toString())
        }
    }
}
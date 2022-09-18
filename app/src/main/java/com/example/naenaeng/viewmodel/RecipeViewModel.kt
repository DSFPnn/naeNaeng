package com.example.naenaeng.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.naenaeng.model.Menu
import com.example.naenaeng.repository.RecipeRepository

class RecipeViewModel:ViewModel() {
    private val _recipeLiveData: MutableLiveData<ArrayList<Menu>>
            = MutableLiveData()
    val recipeLiveData: LiveData<ArrayList<Menu>>
        get() = _recipeLiveData
    private val repo = RecipeRepository()

    fun getRecipe() {
        repo.getData().observeForever{
            _recipeLiveData.value = it.menu
        }
    }
    fun getFilterRecipe(){
        repo.getFilterData().observeForever{
            _recipeLiveData.value = it.menu
        }
    }
}
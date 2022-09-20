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

    private val _filters:MutableLiveData<ArrayList<ArrayList<String>>>
            = MutableLiveData<ArrayList<ArrayList<String>>>()
    val filters: LiveData<ArrayList<ArrayList<String>>>
        get() = _filters

    init {
        _filters.value = ArrayList()
        for(i in 0..3)
            _filters.value!!.add(ArrayList())
    }

    fun getFilter():ArrayList<ArrayList<String>>?{
        return filters.value
    }
    fun setFilter(f:ArrayList<ArrayList<String>>){
        for(i in 0 until f.size){
            _filters.value!![i] = f[i]
        }
        Log.d("filterArrayy _filter", _filters.value.toString())
        Log.d("filterArrayy filter", _filters.value.toString())
    }
}
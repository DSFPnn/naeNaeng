package com.example.naenaeng.ui.home

import android.util.Log
import com.example.naenaeng.MainActivity
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentAddIngredientBinding

class AddIngredientFragment: BaseFragment<FragmentAddIngredientBinding>(R.layout.fragment_add_ingredient) { //음식 추가 화면
    //private lateinit var navController : NavController

    override fun initDataBinding() {
        super.initDataBinding()

        Log.d("whyyy", "왜안돼")
        binding.imgIngredient.clipToOutline = true

    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        Log.d("whyyy", "왜안돼2")
        binding.btnSetIngredient
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).setToolbarTitle("재료 추가")
    }
}
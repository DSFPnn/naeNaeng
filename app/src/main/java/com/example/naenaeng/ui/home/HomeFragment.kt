package com.example.naenaeng.ui.home

import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentHomeBinding

class HomeFragment:BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var navController : NavController

    override fun initDataBinding() {
        super.initDataBinding()

    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnAddIngredient.setOnClickListener{
            navController.navigate(R.id.action_homeFragment_to_addIngredientFragment)
        }
    }

}
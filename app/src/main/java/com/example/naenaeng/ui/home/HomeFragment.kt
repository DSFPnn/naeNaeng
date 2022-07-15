package com.example.naenaeng.ui.home

import android.view.View
import androidx.navigation.*
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.naenaeng.MainActivity
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentHomeBinding

class HomeFragment:BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun initDataBinding() {
        super.initDataBinding()

        binding.btnAddIngredient.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_addIngredientFragment)
        }
    }
}
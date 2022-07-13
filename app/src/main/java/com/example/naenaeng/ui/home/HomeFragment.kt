package com.example.naenaeng.ui.home

import android.view.View
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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
            Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_addIngredientFragment)
            //.findNavController().navigate(ActionOnlyNavDirections())
        }
    }

}
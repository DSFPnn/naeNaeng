package com.example.naenaeng.ui.home

import com.example.naenaeng.MainActivity
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentHomeBinding

class HomeFragment:BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).setToolbar("none")
    }

    override fun initDataBinding() {
        super.initDataBinding()
    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnAddIngredient.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_addIngredientFragment)
        }
    }
}
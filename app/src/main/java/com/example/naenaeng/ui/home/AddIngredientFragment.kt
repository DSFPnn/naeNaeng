package com.example.naenaeng.ui.home

import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentAddIngredientBinding

class AddIngredientFragment: BaseFragment<FragmentAddIngredientBinding>(R.layout.fragment_add_ingredient) { //음식 추가 화면
    private lateinit var navController : NavController

    override fun initDataBinding() {
        super.initDataBinding()

        binding.imgIngredient.clipToOutline = true
    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnSetIngredient.setOnClickListener{
            onDestroy()
        }
        binding.etIngredientName.setOnClickListener {
            navController.navigate(R.id.action_addIngredientFragment_to_ingredientNameFragment)
        }
        binding.etIngredientLife.setOnClickListener {
            navController.navigate(R.id.action_addIngredientFragment_to_ingredientLifeFragment)
        }
    }
}
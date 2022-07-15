package com.example.naenaeng.ui.home

import com.example.naenaeng.MainActivity
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentAddIngredientBinding

class AddIngredientFragment: BaseFragment<FragmentAddIngredientBinding>(R.layout.fragment_add_ingredient) { //음식 추가 화면
    //private lateinit var navController : NavController

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).setToolbar("재료 추가")
    }

    override fun initDataBinding() {
        super.initDataBinding()

        binding.imgIngredient.clipToOutline = true

    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnIngredientName.setOnClickListener {
            navController.navigate(R.id.action_addIngredientFragment_to_ingredientNameFragment)
        }
        binding.btnIngredientLife.setOnClickListener {
            navController.navigate(R.id.action_addIngredientFragment_to_ingredientLifeFragment)
        }
        binding.btnSetIngredient.setOnClickListener{
            navController.navigate(R.id.action_addIngredientFragment_to_homeFragment)
        }
    }

}
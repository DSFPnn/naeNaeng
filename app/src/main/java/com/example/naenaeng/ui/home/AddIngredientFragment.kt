package com.example.naenaeng.ui.home

import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentAddIngredientBinding

class AddIngredientFragment: BaseFragment<FragmentAddIngredientBinding>(R.layout.fragment_add_ingredient) {
    override fun initDataBinding() {
        super.initDataBinding()

        binding.imageView3.clipToOutline = true
    }
}
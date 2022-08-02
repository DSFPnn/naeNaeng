package com.example.naenaeng.ui.recipe

import com.example.naenaeng.MainActivity
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentRecipeBinding

class RecipeFragment : BaseFragment<FragmentRecipeBinding>(R.layout.fragment_recipe) {
    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).setToolbarTitle("레시피")
    }
}
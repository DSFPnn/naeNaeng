package com.example.naenaeng.ui.home

import com.example.naenaeng.R
import com.example.naenaeng.base.BaseDialogFragment
import com.example.naenaeng.databinding.FragmentIngredientLifeBinding

class IngredientLifeFragment : BaseDialogFragment<FragmentIngredientLifeBinding>(R.layout.fragment_ingredient_life){
    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnSetLife.setOnClickListener{
            dismiss()
        }
    }

}
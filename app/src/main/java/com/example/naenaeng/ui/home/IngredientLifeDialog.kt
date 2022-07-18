package com.example.naenaeng.ui.home

import com.example.naenaeng.R
import com.example.naenaeng.base.BaseDialogFragment
import com.example.naenaeng.databinding.DialogIngredientLifeBinding

class IngredientLifeDialog : BaseDialogFragment<DialogIngredientLifeBinding>(R.layout.dialog_ingredient_life){
    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnSetLife.setOnClickListener{
            dismiss()
        }
    }

}
package com.example.naenaeng.ui.home

import com.example.naenaeng.R
import com.example.naenaeng.base.BaseDialogFragment
import com.example.naenaeng.databinding.DialogIngredientNameBinding

class IngredientNameDialog :BaseDialogFragment<DialogIngredientNameBinding>(R.layout.dialog_ingredient_name){
    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnSetName.setOnClickListener{
            dismiss()
        }
    }

}
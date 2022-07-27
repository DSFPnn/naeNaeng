package com.example.naenaeng.ui.home

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseDialogFragment
import com.example.naenaeng.databinding.DialogIngredientNameBinding


class IngredientNameDialog :BaseDialogFragment<DialogIngredientNameBinding>(R.layout.dialog_ingredient_name){

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnSetName.setOnClickListener{
            val ingredient = binding.etId.text.toString()
            setFragmentResult("requestIngredient", bundleOf("ingredient" to ingredient))
            dismiss()
        }
    }


}
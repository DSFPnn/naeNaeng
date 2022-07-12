package com.example.naenaeng.ui.home

import androidx.fragment.app.DialogFragment
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseDialogFragment
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentIngredientNameBinding

class IngredientNameFragment :BaseDialogFragment<FragmentIngredientNameBinding>(R.layout.fragment_ingredient_name){
    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnSetName.setOnClickListener{
            dismiss()
        }
    }

}
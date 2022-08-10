package com.example.naenaeng.ui.recipe

import com.example.naenaeng.R
import com.example.naenaeng.base.BaseBottomDialogFragment
import com.example.naenaeng.databinding.DialogReceipeFilterBinding

class RecipeFilterDialog  : BaseBottomDialogFragment<DialogReceipeFilterBinding>(R.layout.dialog_receipe_filter) {
    override fun initDataBinding() {
        super.initDataBinding()
    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnSetPreference.setOnClickListener {
            dismiss()
        }
    }
}
package com.example.naenaeng.ui.info

import com.example.naenaeng.R
import com.example.naenaeng.base.BaseBottomDialogFragment
import com.example.naenaeng.base.BaseDialogFragment
import com.example.naenaeng.databinding.DialogAllergyBinding

class AllergyDialog : BaseBottomDialogFragment<DialogAllergyBinding>(R.layout.dialog_allergy) {

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnNext.setOnClickListener {
            dismiss()
        }
    }
}
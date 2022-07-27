package com.example.naenaeng.ui.info

import com.example.naenaeng.R
import com.example.naenaeng.base.BaseBottomDialogFragment
import com.example.naenaeng.base.BaseDialogFragment
import com.example.naenaeng.databinding.DialogPreferenceBinding

class PreferenceDialog : BaseBottomDialogFragment<DialogPreferenceBinding>(R.layout.dialog_preference) {
    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnSetPreference.setOnClickListener {
            dismiss()
        }
    }
}
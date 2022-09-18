package com.example.naenaeng.ui.home

import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseDialogFragment
import com.example.naenaeng.databinding.DialogSelectAddModeBinding

class SelectAddModeDialog : BaseDialogFragment<DialogSelectAddModeBinding>(R.layout.dialog_select_add_mode) {

    override fun initDataBinding() {
        super.initDataBinding()

        binding.btnCloseDialog.setOnClickListener {
            dismiss()
        }
        binding.btnManualAdd.setOnClickListener {
            setFragmentResult("requestAddMode", bundleOf("addMode" to "manual"))
            dismiss()
        }
        binding.btnAutomaticAdd.setOnClickListener {
            setFragmentResult("requestAddMode", bundleOf("addMode" to "automatic"))
            dismiss()
        }
    }
}
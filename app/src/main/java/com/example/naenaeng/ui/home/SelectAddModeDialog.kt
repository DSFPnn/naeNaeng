package com.example.naenaeng.ui.home

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseDialogFragment
import com.example.naenaeng.databinding.DialogSelectAddModeBinding

class SelectAddModeDialog : BaseDialogFragment<DialogSelectAddModeBinding>(R.layout.dialog_select_add_mode) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        // false : 화면 밖 터치 혹은 뒤로가기 버튼 누를 시 dismiss 안됨
        isCancelable = true
    }

    override fun initDataBinding() {
        super.initDataBinding()

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
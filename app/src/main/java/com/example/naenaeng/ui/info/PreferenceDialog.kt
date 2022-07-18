package com.example.naenaeng.ui.info

import com.example.naenaeng.R
import com.example.naenaeng.base.BaseBottomDialogFragment
import com.example.naenaeng.base.BaseDialogFragment
import com.example.naenaeng.databinding.DialogPreferenceBinding

class PreferenceDialog : BaseBottomDialogFragment<DialogPreferenceBinding>(R.layout.dialog_preference) {
    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnSetPreference.setOnClickListener {
            // 회원가입 할때는 알러지로 넘어가게, 마이페이지에서 변경할때는 바로 사라지게
            dismiss()
        }
    }
}
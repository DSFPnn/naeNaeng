package com.example.naenaeng.ui.info

import com.example.naenaeng.MainActivity
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseDialogFragment
import com.example.naenaeng.databinding.FragmentPreferenceBinding

class PreferenceFragment : BaseDialogFragment<FragmentPreferenceBinding>(R.layout.fragment_preference) {
    fun getInstance(): PreferenceFragment {
        return PreferenceFragment()
    }
    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnToAllergy.setOnClickListener {
            // 회원가입 할때는 알러지로 넘어가게, 마이페이지에서 변경할때는 바로 사라지게
            dismiss()
        }
    }
}
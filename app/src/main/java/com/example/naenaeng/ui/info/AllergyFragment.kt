package com.example.naenaeng.ui.info

import com.example.naenaeng.MainActivity
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseDialogFragment
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentAllergyBinding

class AllergyFragment : BaseDialogFragment<FragmentAllergyBinding>(R.layout.fragment_allergy) {

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnNext.setOnClickListener {
            dismiss()
        }
    }
}
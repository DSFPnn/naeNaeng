package com.example.naenaeng.ui.info

import com.example.naenaeng.MainActivity
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(R.layout.fragment_register) {
    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).setToolbar("회원가입")
    }

    override fun initDataBinding() {
        super.initDataBinding()
    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnRegister.setOnClickListener {
            navController.navigate(R.id.action_registerFragment_to_preferenceFragment)
        }
    }
}
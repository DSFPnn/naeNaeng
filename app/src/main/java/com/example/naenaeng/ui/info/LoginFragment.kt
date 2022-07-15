package com.example.naenaeng.ui.info

import com.example.naenaeng.MainActivity
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).setToolbar("none2")
    }
    override fun initDataBinding() {
        super.initDataBinding()
    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.tvRegister.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.btnMaster.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }
}
package com.example.naenaeng.ui.mypage

import com.example.naenaeng.MainActivity
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentMyPageBinding

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).setToolbar("마이")
    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnChangePassword.setOnClickListener {
            navController.navigate(R.id.action_myPageFragment_to_changePasswordFragment)
        }
        binding.btnChangePreference.setOnClickListener {
            navController.navigate(R.id.action_myPageFragment_to_preferenceFragment)
        }
        binding.btnChangeAllergy.setOnClickListener {
            navController.navigate(R.id.action_myPageFragment_to_allergyFragment)
        }
        binding.btnLogout.setOnClickListener {
            navController.navigate(R.id.action_myPageFragment_to_loginFragment)
        }
    }
}
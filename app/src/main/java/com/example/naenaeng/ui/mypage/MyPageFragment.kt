package com.example.naenaeng.ui.mypage

import com.example.naenaeng.MainActivity
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentMyPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.example.naenaeng.ui.info.AllergyFragment
import com.example.naenaeng.ui.info.PreferenceFragment

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private lateinit var auth: FirebaseAuth

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).setToolbar("마이")
        auth=Firebase.auth
    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnChangePassword.setOnClickListener {
            navController.navigate(R.id.action_myPageFragment_to_changePasswordFragment)
        }
        binding.btnChangePreference.setOnClickListener {
            PreferenceFragment().show(parentFragmentManager, "preference")
        }
        binding.btnChangeAllergy.setOnClickListener {
            AllergyFragment().show(parentFragmentManager, "preference")
        }
        binding.btnLogout.setOnClickListener {
            //로그아웃
            auth.signOut()
            navController.navigate(R.id.action_myPageFragment_to_loginFragment)
        }
    }
}
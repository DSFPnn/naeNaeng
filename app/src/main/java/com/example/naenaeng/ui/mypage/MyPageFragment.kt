package com.example.naenaeng.ui.mypage

import com.example.naenaeng.MainActivity
import com.example.naenaeng.MyApplication.Companion.prefs
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentMyPageBinding
import com.example.naenaeng.ui.info.AllergyDialog
import com.example.naenaeng.ui.info.PreferenceDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private lateinit var auth: FirebaseAuth

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).setToolbarTitle("마이")
        auth=Firebase.auth
    }

    override fun initDataBinding() {
        super.initDataBinding()

        val name = prefs.getString("name", "null")
        binding.tvHi.text = resources.getString((R.string.hi), name)
    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnChangePassword.setOnClickListener {
            navController.navigate(R.id.action_myPageFragment_to_changePasswordFragment)
        }
        binding.btnChangePreference.setOnClickListener {
            PreferenceDialog().show(parentFragmentManager, "preference")
        }
        binding.btnChangeAllergy.setOnClickListener {
            AllergyDialog().show(parentFragmentManager, "allergy")
        }
        binding.btnLogout.setOnClickListener {
            //로그아웃
            auth.signOut()

            // 저장된 prefs 삭제
            prefs.removeAll()

            navController.navigate(R.id.action_myPageFragment_to_loginFragment)
        }
    }
}
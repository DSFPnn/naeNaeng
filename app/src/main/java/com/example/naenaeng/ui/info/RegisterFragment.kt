package com.example.naenaeng.ui.info

import android.widget.Toast
import com.example.naenaeng.MainActivity
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(R.layout.fragment_register) {
    private lateinit var auth: FirebaseAuth

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).setToolbar("회원가입")
        auth = Firebase.auth

    }

    override fun initDataBinding() {
        super.initDataBinding()
    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnRegister.setOnClickListener {
            var isGoToJoin = true
            val email = binding.etId.text.toString()
            val password = binding.etPassword.text.toString()
            val passwordCheck=binding.etPasswordCheck.text.toString()

            // 유효성 검사
            if (email.isEmpty()) {
                Toast.makeText(context, "이메일을 입력해주세요.", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }
            if (password.isEmpty()) {
                Toast.makeText(context, "비밀번호를 입력해주세요.", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }
            if (passwordCheck.isEmpty()) {
                Toast.makeText(context, "비밀번호 확인을 입력해주세요.", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if (password != passwordCheck) {
                Toast.makeText(context, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            //아이디와 비밀번호로 user 생성
            if (isGoToJoin) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context, "회원가입 성공", Toast.LENGTH_LONG).show()
                            // navController.navigate(R.id.action_registerFragment_to_preferenceFragment)
                        } else {
                            Toast.makeText(context, "회원가입 실패", Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }

        binding.btnBack.setOnClickListener {
            navController.navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
}
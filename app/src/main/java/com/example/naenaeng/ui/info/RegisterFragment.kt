package com.example.naenaeng.ui.info

import android.util.Patterns
import android.widget.Toast
import com.example.naenaeng.MainActivity
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(R.layout.fragment_register) {
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore

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

        binding.btnBack.setOnClickListener {
            navController.navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.btnRegister.setOnClickListener {
            var isGoToJoin = true
            var EmptyCheck = true
            val EmptyString  = emptyList<String>().toMutableList()
            val email = binding.etId.text.toString()
            val password = binding.etPassword.text.toString()
            val passwordCheck=binding.etPasswordCheck.text.toString()


            // 유효성 검사
            if(email.isEmpty())
                EmptyString.add("이메일")
            if(password.isEmpty())
                EmptyString.add("비밀번호")
            if(passwordCheck.isEmpty())
                EmptyString.add("비밀번호 확인")

            if(email.isEmpty() or password.isEmpty() or passwordCheck.isEmpty()){
                Toast.makeText(context, EmptyString.toString()+" 을/를 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
                EmptyCheck = false
            }


            if (EmptyCheck and (password != passwordCheck)) {
                Toast.makeText(context, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if (EmptyCheck and (password == passwordCheck) and !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(context, "이메일 형식이 아닙니다", Toast.LENGTH_SHORT).show()
                binding.etId.setText("")
                isGoToJoin = false
            }

            //아이디와 비밀번호로 user 생성
            if (isGoToJoin) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            AllergyDialog().show(parentFragmentManager, "preference")
                            PreferenceDialog().show(parentFragmentManager, "preference")

                            Toast.makeText(context, "회원가입 성공", Toast.LENGTH_LONG).show()

                            //firestore에 유저정보 저장
                            val data = hashMapOf(
                                "email" to email
                            )
                            db.collection("users").document(email).set(data)

                            navController.navigate(R.id.action_registerFragment_to_loginFragment)
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
package com.example.naenaeng.ui.info

import android.util.Patterns
import android.widget.Toast
import com.example.naenaeng.MainActivity
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private final var FINISH_INTERVAL_TIME: Long = 2000
    private var backPressedTime: Long = 0
    private lateinit var auth: FirebaseAuth



    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).setToolbar("none2")
        auth = Firebase.auth

        //로그인 되어있는지 확인
        val currentUser = auth.currentUser
        if(currentUser != null) {
            navController.navigate(R.id.action_loginFragment_to_homeFragment)
        }
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

        binding.btnRegister.setOnClickListener {
            val ToastText = mutableListOf<String>()
            var isGoToLogin = true
            val email = binding.etId.text.toString()
            val password = binding.etPassword.text.toString()

            // 유효성 검사
            if (email.isEmpty() and !password.isEmpty()) {
                Toast.makeText(context, "이메일을 입력해주세요.", Toast.LENGTH_LONG).show()
                isGoToLogin = false
            }

            if (password.isEmpty() and !email.isEmpty()) {
                Toast.makeText(context, "비밀번호를 입력해주세요.", Toast.LENGTH_LONG).show()
                isGoToLogin = false
            }

            if (email.isEmpty() and password.isEmpty()){
                Toast.makeText(context, "이메일과 비밀번호를 입력해주세요.", Toast.LENGTH_LONG).show()
                isGoToLogin = false
            }

            if (!email.isEmpty() and !password.isEmpty() and !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(context, "이메일 형식이 아닙니다.", Toast.LENGTH_SHORT).show()
                binding.etId.setText("")
                isGoToLogin = false
            }

            //로그인
            if(isGoToLogin){
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context, "로그인 성공",Toast.LENGTH_SHORT).show()
                            navController.navigate(R.id.action_loginFragment_to_homeFragment)
                        } else {
                            Toast.makeText(context, "로그인 실패",Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

    }
}
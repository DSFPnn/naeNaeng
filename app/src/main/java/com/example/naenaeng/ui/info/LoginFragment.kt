package com.example.naenaeng.ui.info

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.naenaeng.MainActivity
import com.example.naenaeng.MyApplication.Companion.prefs
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private final var FINISH_INTERVAL_TIME: Long = 2000
    private var backPressedTime: Long = 0
    private lateinit var auth: FirebaseAuth

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).setToolbarTitle("none2")
        auth = Firebase.auth

        //로그인 되어있는지 확인
        val currentUser = auth.currentUser
        if(currentUser != null) {
            navController.navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.tvRegister.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.tvFindIdPw.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_findIdPwFragment)
        }

        binding.btnRegister.setOnClickListener {
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
                            //preferences에 유저 이메일 저장
                            prefs.setString("email",email)
                            setName(email)
                            Toast.makeText(context, "로그인 성공",Toast.LENGTH_SHORT).show()

                            navController.navigate(R.id.action_loginFragment_to_homeFragment)
                        } else {
                            Toast.makeText(context, "로그인 실패",Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

    }

    private fun setName(email:String) {
        val db = Firebase.firestore
        // 앱 저장소에 이름 저장
        db.collection("users").document(email).get()
            .addOnSuccessListener { document ->
                var name = document.get("name").toString()
                prefs.setString("name", name)
            }
            .addOnFailureListener{
                Log.d("error LoginFragment", "null")
            }
    }
}
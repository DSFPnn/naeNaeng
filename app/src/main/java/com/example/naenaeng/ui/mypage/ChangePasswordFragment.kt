package com.example.naenaeng.ui.mypage

import android.os.Debug
import android.util.Log
import android.widget.Toast
import com.example.naenaeng.MainActivity
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentChangePasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding>(R.layout.fragment_change_password) {
    private lateinit var auth: FirebaseAuth

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).setToolbar("비밀번호 변경")
        auth = Firebase.auth

    }

    override fun initDataBinding() {
        super.initDataBinding()
    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnRegister.setOnClickListener {
            var isGoToChange = true
            val emailCheck = binding.btnIngredientName.text.toString()
            val newPassword = binding.btnIngredientLife.text.toString()
            val newPasswordCheck = binding.etPasswordCheck.text.toString()

            val user = Firebase.auth.currentUser
            val email = user?.email

            if(user?.email != emailCheck){
                Toast.makeText(context, "이메일이 일치하지 않습니다.", Toast.LENGTH_LONG).show()
                isGoToChange = false
            }

            if (newPassword != newPasswordCheck) {
                Toast.makeText(context, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show()
                isGoToChange = false
            }

            if (isGoToChange) {
                user!!.updatePassword(newPassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context, "비밀번호가 변경되었습니다.", Toast.LENGTH_LONG).show()
                            navController.navigate(R.id.action_changePasswordFragment_to_myPageFragment)
                        }
                        else{
                            Toast.makeText(context, "비밀번호 실패.", Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }
    }
}
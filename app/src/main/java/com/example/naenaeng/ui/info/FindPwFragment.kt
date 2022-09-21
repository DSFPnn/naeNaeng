package com.example.naenaeng.ui.info

import android.app.AlertDialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.util.Log
import android.widget.Toast
import com.example.naenaeng.MainActivity
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentFindPwBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FindPwFragment: BaseFragment<FragmentFindPwBinding>(R.layout.fragment_find_pw) {
    private val db = Firebase.firestore

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).setToolbarTitle("none3")

    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnFindPw.setOnClickListener {
            val email = binding.etEmailforPw.text.toString()

            if(email.isEmpty()){
                Toast.makeText(context,"이메일을 입력해주세요", Toast.LENGTH_LONG).show()
            }


            db.collection("users")
                .whereEqualTo("email", email)
                .get()
                .addOnSuccessListener { documents ->
                    if (documents.size() == 0){
                        AlertDialog.Builder(context).run {
                            setTitle("비밀번호 찾기")
                            setIcon(R.drawable.icon_naenaeng)
                            setMessage("가입정보가 없습니다. 다시 한번 확인해주세요")
                            setPositiveButton("확인",null)
                            show()
                        }
                        binding.etEmailforPw.setText("")
                    }
                    for (document in documents) {
                        Firebase.auth.sendPasswordResetEmail(email)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    AlertDialog.Builder(context).run {
                                        setTitle("비밀번호 찾기")
                                        setIcon(R.drawable.icon_naenaeng)
                                        setMessage("이메일주소로 비밀번호 변경링크를 보냈습니다.")
                                        setPositiveButton("확인",
                                            DialogInterface.OnClickListener { dialog, id ->
                                                navController.navigate(R.id.action_findIdPwFragment_to_loginFragment)
                                            })
                                        show()
                                    }
                                }
                            }

                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                }
        }
    }
}
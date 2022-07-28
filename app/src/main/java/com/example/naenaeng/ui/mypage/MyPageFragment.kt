package com.example.naenaeng.ui.mypage

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import com.example.naenaeng.MainActivity
import com.example.naenaeng.MyApplication
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentMyPageBinding
import com.example.naenaeng.model.User
import com.example.naenaeng.ui.info.AllergyDialog
import com.example.naenaeng.ui.info.PreferenceDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore

    @SuppressLint("SetTextI18n")
    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).setToolbar("마이")
        auth=Firebase.auth

        //사용자 이름 변경
        db.collection("users").document(MyApplication.prefs.getString("email","null")).get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot != null) {
                    val data = documentSnapshot.toObject<User>()
                    if (data != null) {
                        binding.tvHi.text = "${data.name}님, 안녕하세요! 필요하신 부분이 있으실까요?"
                    }
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }
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
            AllergyDialog().show(parentFragmentManager, "preference")
        }
        binding.btnLogout.setOnClickListener {
            //로그아웃
            auth.signOut()
            navController.navigate(R.id.action_myPageFragment_to_loginFragment)
        }
    }
}
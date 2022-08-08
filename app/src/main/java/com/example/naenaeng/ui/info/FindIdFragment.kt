package com.example.naenaeng.ui.info

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.util.Log
import android.widget.Toast
import com.example.naenaeng.MainActivity
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentFindIdBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FindIdFragment : BaseFragment<FragmentFindIdBinding>(R.layout.fragment_find_id) {
    private val db = Firebase.firestore

    override fun initStartView() {
            super.initStartView()
            (activity as MainActivity).setToolbarTitle("ID/PW찾기")

        }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnFindId.setOnClickListener {
            val name = binding.etNameforId.text.toString()

            if(name.isEmpty()){
                Toast.makeText(context,"이름을 입력해주세요", Toast.LENGTH_LONG).show()
            }

            db.collection("users")
                .whereEqualTo("name", name)
                .get()
                .addOnSuccessListener { documents ->
                    if (documents.size() == 0){
                        AlertDialog.Builder(context).run {
                            setTitle("아이디 찾기")
                            setIcon(R.drawable.icon_naenaeng)
                            setMessage("가입정보가 없습니다. 이름을 확인해주세요")
                            setPositiveButton("확인",null)
                            show()
                        }
                        binding.etNameforId.setText("")
                    }
                    for (document in documents) {
                        AlertDialog.Builder(context).run {
                            setTitle("아이디 찾기")
                            setIcon(R.drawable.icon_naenaeng)
                            setMessage("${name}님의 아이디는 ${document.id}입니다.")
                            setPositiveButton("확인",
                                DialogInterface.OnClickListener { dialog, id ->
                                navController.navigate(R.id.action_findIdPwFragment_to_loginFragment)
                            })
                            show()
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents: ", exception)
                }
        }
    }
}

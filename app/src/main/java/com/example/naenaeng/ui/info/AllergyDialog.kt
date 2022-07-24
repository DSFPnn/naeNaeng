package com.example.naenaeng.ui.info

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseBottomDialogFragment
import com.example.naenaeng.databinding.DialogAllergyBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AllergyDialog : BaseBottomDialogFragment<DialogAllergyBinding>(R.layout.dialog_allergy) {
    private lateinit var allergyAdapter:AllergyAdapter
    private val db = Firebase.firestore
    private var datas = mutableListOf<String>()

    @SuppressLint("NotifyDataSetChanged")
    override fun initDataBinding() {
        super.initDataBinding()

        //DB에서 알러지이름 가져오기
        db.collection("allergy").document("allergyName").get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    var data = document.data?.get("names").toString()
                    data = data.substring(1..data.length-2)
                    val array = data.split(",")
                    for (item in array)
                        datas.add(item.trim())
                    allergyAdapter.notifyDataSetChanged()
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }


        val layoutManager = LinearLayoutManager(activity)
        binding.allergyRecyclerView.layoutManager=layoutManager
        allergyAdapter=AllergyAdapter(datas)
        binding.allergyRecyclerView.adapter=allergyAdapter

    }
    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnNext.setOnClickListener {

        }
    }


}
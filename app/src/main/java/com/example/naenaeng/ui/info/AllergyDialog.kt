package com.example.naenaeng.ui.info

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseBottomDialogFragment
import com.example.naenaeng.databinding.DialogAllergyBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AllergyDialog : BaseBottomDialogFragment<DialogAllergyBinding>(R.layout.dialog_allergy) {
    private lateinit var database: DatabaseReference
    private lateinit var allergyAdapter:AllergyAdapter

    override fun initDataBinding() {
        super.initDataBinding()
        database = Firebase.database.reference

        //알레르기 데이터 받아오기
        val datas = mutableListOf<String>()
        val myTopPostsQuery = database.child("allergy").orderByKey()

        myTopPostsQuery.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    datas.add("${postSnapshot.value.toString()} 알러지")
                    allergyAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())

            }
        })


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
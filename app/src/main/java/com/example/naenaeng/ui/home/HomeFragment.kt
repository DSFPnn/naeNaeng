package com.example.naenaeng.ui.home

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.naenaeng.MainActivity
import com.example.naenaeng.MyApplication.Companion.prefs
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentHomeBinding
import com.example.naenaeng.model.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


class HomeFragment:BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var HomeIngredientAdapter:HomeIngredientAdapter
    private val db = Firebase.firestore
    private var ingredients = ArrayList<HashMap<String, String>>()

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).setToolbar("none")
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun initDataBinding() {
        super.initDataBinding()

        //firestore에서 재료데이터 가져오기
        db.collection("users").document(prefs.getString("email","null")).get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot != null) {
                    val data = documentSnapshot.toObject<User>()
                    if (data != null) {
                        ingredients = data.ingredients!!
                    }
                    HomeIngredientAdapter.notifyDataSetChanged()
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }

        val layoutManager = LinearLayoutManager(activity)
        binding.ingredientRecyclerView.layoutManager=layoutManager
        HomeIngredientAdapter= HomeIngredientAdapter(ingredients)
        binding.ingredientRecyclerView.adapter=HomeIngredientAdapter
    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnAddIngredient.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_addIngredientFragment)
        }


    }
}
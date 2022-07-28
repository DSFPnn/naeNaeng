package com.example.naenaeng.ui.home

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.naenaeng.MainActivity
import com.example.naenaeng.MyApplication.Companion.prefs
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentHomeBinding
import com.example.naenaeng.model.User
import com.example.naenaeng.ui.info.AllergyAdapter
import com.example.naenaeng.viewmodel.AllergyViewModel
import com.example.naenaeng.viewmodel.UserViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


class HomeFragment:BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var homeIngredientAdapter:HomeIngredientAdapter
    private val db = Firebase.firestore
    private var ingredients = ArrayList<HashMap<String, String>>()

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).setToolbar("none")
    }
    private val viewModel by lazy {
        ViewModelProvider(this).get(UserViewModel::class.java)
    }

    override fun initDataBinding() {
        super.initDataBinding()

        homeIngredientAdapter= HomeIngredientAdapter(ArrayList())

        binding.ingredientRecyclerView.adapter=homeIngredientAdapter

        viewModel.getUserIngredient()

        viewModel.userIngredientLiveData.observe(viewLifecycleOwner) { itemList ->
            homeIngredientAdapter.itemList = itemList
            Log.d("ingredd", itemList.toString())
        }
    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnAddIngredient.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_addIngredientFragment)
        }
    }
}
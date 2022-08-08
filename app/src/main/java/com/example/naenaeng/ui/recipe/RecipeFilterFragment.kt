package com.example.naenaeng.ui.recipe

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.naenaeng.MyApplication
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentReceipeFilterBinding
import com.example.naenaeng.model.User
import com.example.naenaeng.viewmodel.PreferenceViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class RecipeFilterFragment : BaseFragment<FragmentReceipeFilterBinding>(R.layout.fragment_receipe_filter) {
    private lateinit var preferenceIndexAdapter: RecipeFilterAdatper
    private lateinit var preferenceTasteAdapter: RecipeFilterAdatper
    private lateinit var preferenceSpicyAdapter: RecipeFilterAdatper

    private val viewModel by lazy {
        ViewModelProvider(this).get(PreferenceViewModel::class.java)
    }

    override fun initStartView() {
        super.initStartView()


        preferenceIndexAdapter = RecipeFilterAdatper(ArrayList())
        preferenceTasteAdapter = RecipeFilterAdatper(ArrayList())
        preferenceSpicyAdapter = RecipeFilterAdatper(ArrayList())

        binding.preIndexRecyclerView.adapter = preferenceIndexAdapter
        binding.preTasteRecyclerView.adapter = preferenceTasteAdapter
        binding.preSpicyRecyclerView.adapter = preferenceSpicyAdapter

        viewModel.getPreference()

        viewModel.preferenceLiveData.observe(viewLifecycleOwner) { itemList ->
            preferenceIndexAdapter.itemList = itemList.index
            preferenceTasteAdapter.itemList = itemList.taste
            preferenceSpicyAdapter.itemList = itemList.spicy
        }
    }

    override fun initDataBinding() {
        super.initDataBinding()

        binding.btnSetPreference.setOnClickListener {
            navController.navigate(R.id.action_recipeFilterFragment_to_recipeFragment)
        }

        binding.btnInitialization.setOnClickListener {
            //
        }
    }
}
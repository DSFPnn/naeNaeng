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

    override fun initStartView() {
        super.initStartView()


    }

    override fun initDataBinding() {

        binding.btnSetPreference.setOnClickListener {
            navController.navigate(R.id.action_recipeFilterFragment_to_recipeFragment)
        }

    }
}
package com.example.naenaeng.ui.recipe

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.naenaeng.MainActivity
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentRecipeBinding
import com.example.naenaeng.ui.info.AllergyAdapter
import com.example.naenaeng.viewmodel.AllergyViewModel
import com.example.naenaeng.viewmodel.RecipeViewModel

class RecipeFragment : BaseFragment<FragmentRecipeBinding>(R.layout.fragment_recipe) {
    private lateinit var recipeAdapter: RecipeAdapter

    private val viewModel by lazy {
        ViewModelProvider(this).get(RecipeViewModel::class.java)
    }

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).setToolbarTitle("레시피")
    }

    override fun initDataBinding() {
        super.initDataBinding()
        recipeAdapter= RecipeAdapter(ArrayList())

        binding.recipeRecyclerView.adapter=recipeAdapter

        viewModel.getRecipe()

        viewModel.recipeLiveData.observe(viewLifecycleOwner) { itemList ->
            recipeAdapter.itemList = itemList
            Log.d("recipee",itemList.toString())
        }
    }
}
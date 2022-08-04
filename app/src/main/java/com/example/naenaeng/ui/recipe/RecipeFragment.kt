package com.example.naenaeng.ui.recipe

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.naenaeng.MainActivity
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentRecipeBinding
import com.example.naenaeng.model.Ingredient
import com.example.naenaeng.model.Menu
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

        binding.etSearchRecipe.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val searchString = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // TODO("not implemented")
                // To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchItemList: ArrayList<Menu> = ArrayList<Menu>()
                val searchString = binding.etSearchRecipe.text
                viewModel.recipeLiveData.observe(viewLifecycleOwner) { itemList ->
                    for (item in itemList){
                        if(item.title.contains(searchString)){
                            searchItemList.add(item)
                        }
                    }
                    recipeAdapter.itemList = searchItemList

                    if (binding.etSearchRecipe.text.isEmpty()) {
                        viewModel.recipeLiveData.observe(viewLifecycleOwner) { itemList ->
                            recipeAdapter.itemList = itemList
                        }
                    }
                }
            }
        })
    }
}
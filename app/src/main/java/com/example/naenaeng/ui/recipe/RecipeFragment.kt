package com.example.naenaeng.ui.recipe

import android.graphics.DrawFilter
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import com.example.naenaeng.MainActivity
import com.example.naenaeng.MyApplication
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentRecipeBinding
import com.example.naenaeng.model.Ingredient
import com.example.naenaeng.model.Menu
import com.example.naenaeng.ui.home.HomeIngredientAdapter
import com.example.naenaeng.ui.home.IngredientDateDialog
import com.example.naenaeng.ui.info.AllergyAdapter
import com.example.naenaeng.ui.info.AllergyDialog
import com.example.naenaeng.viewmodel.AllergyViewModel
import com.example.naenaeng.viewmodel.RecipeViewModel
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FieldValue

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


        //필터기능
        binding.btnFilter.setOnClickListener {
            RecipeFilterDialog().show(parentFragmentManager, "filter")
        }

        //검색기능
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

    override fun initAfterBinding() {
        super.initAfterBinding()

        recipeAdapter.setItemClickListener(object: RecipeAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int, m:String) {
                // 클릭 시 이벤트 작성
                Log.d("clickk","${recipeAdapter.itemList[position].title}")

                RecipeDetailDialog(recipeAdapter.itemList[position]).show(parentFragmentManager, "recipeDetail")
            }
        })
    }
}
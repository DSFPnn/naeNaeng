package com.example.naenaeng.ui.recipe

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.example.naenaeng.MainActivity
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentRecipeBinding
import com.example.naenaeng.model.Menu
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

        // 필터 받아오기 (0:나라, 1:맛, 2:조리방법, 3:알러지)
        setFragmentResultListener("requestFilter") { _, bundle ->
            val result = bundle.get("filterArray") as ArrayList<ArrayList<String>>

            if(result[0].isNotEmpty()) {
                Log.d("resultt", "0${result[0]}")
                binding.btnFilterCountry.text = if(result[0].size>1) "나라 ${result[0].size}" else result[0].get(0)
                binding.btnFilterCountry.background = ResourcesCompat.getDrawable(resources, R.drawable.blue_solid_radius20, null)
            }
            else{
                binding.btnFilterCountry.text = "나라"
                binding.btnFilterCountry.background = ResourcesCompat.getDrawable(resources, R.drawable.white_solid_outline_radius20, null)
            }

            if(result[1].isNotEmpty()) {
                Log.d("resultt", "1${result[1]}")
                binding.btnFilterTaste.text = if(result[1].size>1) "맛 ${result[1].size}" else result[1].get(0)
                binding.btnFilterTaste.background = ResourcesCompat.getDrawable(resources, R.drawable.blue_solid_radius20, null)
            }
            else{
                binding.btnFilterTaste.text = "맛"
                binding.btnFilterTaste.background = ResourcesCompat.getDrawable(resources, R.drawable.white_solid_outline_radius20, null)
            }

            if(result[2].isNotEmpty()) {
                Log.d("resultt", "2${result[2]}")
                binding.btnFilterCook.text = if(result[2].size>1) "조리 방법 ${result[2].size}" else result[2].get(0)
                binding.btnFilterCook.background = ResourcesCompat.getDrawable(resources, R.drawable.blue_solid_radius20, null)
            }
            else{
                binding.btnFilterCook.text = "조리 방법"
                binding.btnFilterCook.background = ResourcesCompat.getDrawable(resources, R.drawable.white_solid_outline_radius20, null)
            }

            if(result[3].isNotEmpty()) {
                Log.d("resultt", "3${result[3]}")
                binding.btnFilterAllergy.text = if(result[3].size>1) "알러지 필터링 ${result[3].size}" else result[3].get(0)
                binding.btnFilterAllergy.background = ResourcesCompat.getDrawable(resources, R.drawable.blue_solid_radius20, null)
            }
            else{
                binding.btnFilterAllergy.text = "알러지 필터링"
                binding.btnFilterAllergy.background = ResourcesCompat.getDrawable(resources, R.drawable.white_solid_outline_radius20, null)
            }
        }
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

                for (item in viewModel.recipeLiveData.value!!){
                    if(item.title.contains(searchString)){
                        searchItemList.add(item)
                    }
                }
                recipeAdapter.itemList = searchItemList

                if (binding.etSearchRecipe.text.isEmpty()) {
                    viewModel.getRecipe()
                }
            }
        })
    }
}
package com.example.naenaeng.ui.home

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.naenaeng.MainActivity
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentHomeBinding
import com.example.naenaeng.model.Ingredient
import com.example.naenaeng.viewmodel.UserViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class HomeFragment:BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var homeIngredientAdapter:HomeIngredientAdapter
    val db = Firebase.firestore

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).setToolbarTitle("none")
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

        binding.etSearchIngredient.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val searchString = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // TODO("not implemented")
                // To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchItemList: ArrayList<Ingredient> = ArrayList<Ingredient>()
                val searchString = binding.etSearchIngredient.text
                viewModel.userIngredientLiveData.observe(viewLifecycleOwner) { itemList ->
                    for (item in itemList){
                        if(item.name.contains(searchString)){
                            searchItemList.add(item)
                        }
                    }
                    homeIngredientAdapter.itemList = searchItemList

                if (binding.etSearchIngredient.text.isEmpty()) {
                    viewModel.userIngredientLiveData.observe(viewLifecycleOwner) { itemList ->
                        homeIngredientAdapter.itemList = itemList
                    }
                }
            }
        }
    })
} }

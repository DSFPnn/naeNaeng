package com.example.naenaeng.ui.home

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.naenaeng.MainActivity
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentHomeBinding
import com.example.naenaeng.viewmodel.UserViewModel


class HomeFragment:BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var homeIngredientAdapter:HomeIngredientAdapter

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

    }
}

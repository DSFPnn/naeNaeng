package com.example.naenaeng.ui.recipe

import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseBottomDialogFragment
import com.example.naenaeng.databinding.DialogReceipeFilterBinding
import com.example.naenaeng.viewmodel.PreferenceViewModel

class RecipeFilterDialog  : BaseBottomDialogFragment<DialogReceipeFilterBinding>(R.layout.dialog_receipe_filter) {

    private lateinit var filterCountryAdapter: RecipeFilterAdapter
    private lateinit var filterTasteAdapter: RecipeFilterAdapter
    private lateinit var filterCookAdapter: RecipeFilterAdapter
    private lateinit var filterAllergyAdapter: RecipeFilterAdapter

    var filterArray:ArrayList<ArrayList<String>> = ArrayList(ArrayList())

    private val viewModel by lazy {
        ViewModelProvider(this).get(PreferenceViewModel::class.java)
    }

    override fun initDataBinding() {
        super.initDataBinding()

        Log.d("refreshh","initdata")
        filterCountryAdapter = RecipeFilterAdapter(ArrayList())
        filterTasteAdapter = RecipeFilterAdapter(ArrayList())
        filterCookAdapter = RecipeFilterAdapter(ArrayList())
        filterAllergyAdapter = RecipeFilterAdapter(ArrayList())

        binding.filterCountryRecyclerView.adapter = filterCountryAdapter
        binding.filterTasteRecyclerView.adapter = filterTasteAdapter
        binding.filterCookRecyclerView.adapter = filterCookAdapter
        binding.filterAllergyRecyclerView.adapter = filterAllergyAdapter

        viewModel.getPreference()

        viewModel.preferenceLiveData.observe(viewLifecycleOwner) { itemList ->
            filterCountryAdapter.itemList = itemList.country
            filterTasteAdapter.itemList = itemList.taste
            filterCookAdapter.itemList = itemList.cook
            filterAllergyAdapter.itemList = itemList.allergy
            Log.d("refreshh","1")
        }
    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnInitialization.setOnClickListener {
            filterCountryAdapter = RecipeFilterAdapter(ArrayList())
            filterTasteAdapter = RecipeFilterAdapter(ArrayList())
            filterCookAdapter = RecipeFilterAdapter(ArrayList())
            filterAllergyAdapter = RecipeFilterAdapter(ArrayList())

            binding.filterCountryRecyclerView.adapter = filterCountryAdapter
            binding.filterTasteRecyclerView.adapter = filterTasteAdapter
            binding.filterCookRecyclerView.adapter = filterCookAdapter
            binding.filterAllergyRecyclerView.adapter = filterAllergyAdapter

            viewModel.getPreference()

            Log.d("refreshh", filterAllergyAdapter.filterDatas.toString())
        }
        binding.btnSetPreference.setOnClickListener {
            filterArray.add(filterCountryAdapter.filterDatas)
            filterArray.add(filterTasteAdapter.filterDatas)
            filterArray.add(filterCookAdapter.filterDatas)
            filterArray.add(filterAllergyAdapter.filterDatas)
            Log.d("filterrD",filterArray.toString())
            setFragmentResult("requestFilter", bundleOf("filterArray" to filterArray))
            dismiss()
        }
    }
}
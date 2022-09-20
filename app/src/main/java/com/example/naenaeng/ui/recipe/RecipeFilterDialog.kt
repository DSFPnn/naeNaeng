package com.example.naenaeng.ui.recipe

import android.util.Log
import android.view.View
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
import com.example.naenaeng.viewmodel.RecipeViewModel

class RecipeFilterDialog  : BaseBottomDialogFragment<DialogReceipeFilterBinding>(R.layout.dialog_receipe_filter) {

    private lateinit var filterCountryAdapter: RecipeFilterAdapter
    private lateinit var filterTasteAdapter: RecipeFilterAdapter
    private lateinit var filterCookAdapter: RecipeFilterAdapter
    private lateinit var filterAllergyAdapter: RecipeFilterAdapter
    private lateinit var filterArrayArray:ArrayList<ArrayList<String>> // 사용자가 선택한 필터

    private val viewModel by lazy {
        ViewModelProvider(this).get(PreferenceViewModel::class.java)
    }
    private val viewModelFilter by lazy{
        ViewModelProvider(requireParentFragment()).get(RecipeViewModel::class.java)
    }

    override fun initDataBinding() {
        super.initDataBinding()

        filterArrayArray = viewModelFilter.getFilter()!!

        // 0 나라, 1 맛, 2 조리방법, 3 알러지
        filterCountryAdapter = RecipeFilterAdapter(ArrayList())
        filterTasteAdapter = RecipeFilterAdapter(ArrayList())
        filterCookAdapter = RecipeFilterAdapter(ArrayList())
        filterAllergyAdapter = RecipeFilterAdapter(ArrayList())

        Log.d("filterArray 생긴거",filterArrayArray.toString())
        filterCountryAdapter.filterDatas = filterArrayArray[0]
        filterTasteAdapter.filterDatas = filterArrayArray[1]
        filterCookAdapter.filterDatas = filterArrayArray[2]
        filterAllergyAdapter.filterDatas = filterArrayArray[3]

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

            viewModelFilter.setFilter(ArrayList())
            viewModel.getPreference()
        }

        binding.btnSetPreference.setOnClickListener {
            filterArrayArray[0] = filterCountryAdapter.filterDatas
            filterArrayArray[1] = filterTasteAdapter.filterDatas
            filterArrayArray[2] = filterCookAdapter.filterDatas
            filterArrayArray[3] = filterAllergyAdapter.filterDatas
            setFragmentResult("requestFilter", bundleOf("filterArray" to filterArrayArray))

            viewModelFilter.setFilter(filterArrayArray)

            dismiss()
        }
    }
}
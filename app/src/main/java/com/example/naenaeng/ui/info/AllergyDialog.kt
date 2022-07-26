package com.example.naenaeng.ui.info

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseBottomDialogFragment
import com.example.naenaeng.databinding.DialogAllergyBinding
import com.example.naenaeng.model.Allergy
import com.example.naenaeng.viewmodel.AllergyViewModel

class AllergyDialog : BaseBottomDialogFragment<DialogAllergyBinding>(R.layout.dialog_allergy) {
    private lateinit var allergyAdapter:AllergyAdapter

    private val viewModel by lazy {
        ViewModelProvider(this).get(AllergyViewModel::class.java)
    }

    override fun initDataBinding() {
        super.initDataBinding()
        allergyAdapter=AllergyAdapter(ArrayList())

        binding.allergyRecyclerView.adapter=allergyAdapter

        viewModel.getAllergy()

        viewModel.allergyLiveData.observe(viewLifecycleOwner) { itemList ->
            allergyAdapter.itemList = itemList
            Log.d("allergyy",itemList.toString())
        }
    }
    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnNext.setOnClickListener {
            dismiss()
        }
    }
}
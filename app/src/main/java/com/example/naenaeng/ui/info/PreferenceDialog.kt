package com.example.naenaeng.ui.info

import androidx.lifecycle.ViewModelProvider
import com.example.naenaeng.MyApplication
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseBottomDialogFragment
import com.example.naenaeng.databinding.DialogPreferenceBinding
import com.example.naenaeng.repository.UserRepository
import com.example.naenaeng.viewmodel.PreferenceViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PreferenceDialog : BaseBottomDialogFragment<DialogPreferenceBinding>(R.layout.dialog_preference) {
    private val db = Firebase.firestore
    private lateinit var preferenceCountryAdapter: PreferenceAdapter
    private lateinit var preferenceTasteAdapter: PreferenceAdapter
    private lateinit var preferenceCookAdapter: PreferenceAdapter
    var filterArray:ArrayList<String> = ArrayList()

    private val viewModel by lazy {
        ViewModelProvider(this).get(PreferenceViewModel::class.java)
    }

    override fun initDataBinding() {
        super.initDataBinding()
        preferenceCountryAdapter = PreferenceAdapter(ArrayList())
        preferenceTasteAdapter = PreferenceAdapter(ArrayList())
        preferenceCookAdapter = PreferenceAdapter(ArrayList())

        binding.preCountryRecyclerView.adapter = preferenceCountryAdapter
        binding.preTasteRecyclerView.adapter = preferenceTasteAdapter
        binding.preCookRecyclerView.adapter = preferenceCookAdapter

        viewModel.getPreference()

        viewModel.preferenceLiveData.observe(viewLifecycleOwner) { itemList ->
            preferenceCountryAdapter.itemList = itemList.country
            preferenceTasteAdapter.itemList = itemList.taste
            preferenceCookAdapter.itemList = itemList.cook
        }
    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        val dbUser = db.collection("users").document(MyApplication.prefs.getString("email", "null"))

        binding.btnSetPreference.setOnClickListener {
            // 사용자별 선호도 DB에 저장
            dbUser.update("preference.index", preferenceCountryAdapter.preferenceDatas)
            dbUser.update("preference.taste", preferenceTasteAdapter.preferenceDatas)
            dbUser.update("preference.spicy", preferenceCookAdapter.preferenceDatas)

            UserRepository().getData()
            dismiss()
        }
    }
}
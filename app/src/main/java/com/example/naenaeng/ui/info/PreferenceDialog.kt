package com.example.naenaeng.ui.info

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.naenaeng.MyApplication
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseBottomDialogFragment
import com.example.naenaeng.base.BaseDialogFragment
import com.example.naenaeng.databinding.DialogPreferenceBinding
import com.example.naenaeng.viewmodel.AllergyViewModel
import com.example.naenaeng.viewmodel.PreferenceViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.dialog_preference.*

class PreferenceDialog : BaseBottomDialogFragment<DialogPreferenceBinding>(R.layout.dialog_preference) {
    private val db = Firebase.firestore
    private lateinit var preferenceIndexAdapter: PreferenceAdapter
    private lateinit var preferenceTasteAdapter: PreferenceAdapter
    private lateinit var preferenceSpicyAdapter: PreferenceAdapter

    private val viewModel by lazy {
        ViewModelProvider(this).get(PreferenceViewModel::class.java)
    }

    override fun initDataBinding() {
        super.initDataBinding()
        preferenceIndexAdapter = PreferenceAdapter(ArrayList())
        preferenceTasteAdapter = PreferenceAdapter(ArrayList())
        preferenceSpicyAdapter = PreferenceAdapter(ArrayList())

        binding.preIndexRecyclerView.adapter = preferenceIndexAdapter
        binding.preTasteRecyclerView.adapter = preferenceTasteAdapter
        binding.preSpicyRecyclerView.adapter = preferenceSpicyAdapter

        viewModel.getPreference()

        viewModel.preferenceLiveData.observe(viewLifecycleOwner) { itemList ->
            preferenceIndexAdapter.itemList = itemList.index
            preferenceTasteAdapter.itemList = itemList.taste
            preferenceSpicyAdapter.itemList = itemList.spicy
        }
    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        val dbUser = db.collection("users").document(MyApplication.prefs.getString("email", "null"))

        binding.btnSetPreference.setOnClickListener {
            // 사용자별 선호도 DB에 저장
            dbUser.update("preference.index", preferenceIndexAdapter.preferenceDatas)
            dbUser.update("preference.taste", preferenceTasteAdapter.preferenceDatas)
            dbUser.update("preference.spicy", preferenceSpicyAdapter.preferenceDatas)

            dismiss()
        }
    }
}
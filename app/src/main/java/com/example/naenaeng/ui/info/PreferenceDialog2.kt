package com.example.naenaeng.ui.info

import android.widget.TextView
import com.example.naenaeng.MyApplication
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseBottomDialogFragment
import com.example.naenaeng.base.BaseDialogFragment
import com.example.naenaeng.databinding.DialogPreferenceBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.dialog_preference.*

class PreferenceDialog2 : BaseBottomDialogFragment<DialogPreferenceBinding>(R.layout.dialog_preference) {
    /*
    private val db = Firebase.firestore
    private var indexDatas = mutableListOf<String>()
    private var tasteDatas = mutableListOf<String>()
    private var spicyDatas = mutableListOf<String>()

    override fun initAfterBinding() {
        super.initAfterBinding()

        indexOnClickListener()
        tasteOnClickListener()
        spicyOnClickListener()

        binding.btnSetPreference.setOnClickListener {
            val dbUser = db.collection("users").document(MyApplication.prefs.getString("email", "null"))
            dbUser.update("preference.index", indexDatas)
            dbUser.update("preference.taste", tasteDatas)
            dbUser.update("preference.spicy", spicyDatas)

            dismiss()
        }
    }
    private fun indexOnClickListener() {
        binding.tvIndex1.setOnClickListener {
            if(tv_index1.isSelected){
                indexDatas.remove(binding.tvIndex1.text.toString())
                tv_index1.isSelected = false
            } else{
                indexDatas.add(binding.tvIndex1.text.toString())
                tv_index1.isSelected = true
            }
        }
        binding.tvIndex2.setOnClickListener {
            if(tv_index2.isSelected){
                indexDatas.remove(binding.tvIndex1.text.toString())
                tv_index2.isSelected = false
            } else{
                indexDatas.add(binding.tvIndex1.text.toString())
                tv_index2.isSelected = true
            }
        }
        binding.tvIndex3.setOnClickListener {
            tv_index3.isSelected = tv_index3.isSelected != true
        }
        binding.tvIndex4.setOnClickListener {
            tv_index4.isSelected = tv_index4.isSelected != true
        }
        binding.tvIndex5.setOnClickListener {
            tv_index5.isSelected = tv_index5.isSelected != true
        }
    }

    private fun tasteOnClickListener(){
        binding.tvTaste1.setOnClickListener {
            tv_taste1.isSelected = tv_taste1.isSelected != true
        }
        binding.tvTaste2.setOnClickListener {
            tv_taste2.isSelected = tv_taste2.isSelected != true
        }
        binding.tvTaste3.setOnClickListener {
            tv_taste3.isSelected = tv_taste3.isSelected != true
        }
        binding.tvTaste4.setOnClickListener {
            tv_taste4.isSelected = tv_taste4.isSelected != true
        }
        binding.tvTaste5.setOnClickListener {
            tv_taste5.isSelected = tv_taste5.isSelected != true
        }
    }

    private fun spicyOnClickListener(){
        binding.tvSpicy1.setOnClickListener {
            tv_spicy1.isSelected = tv_spicy1.isSelected != true
        }
        binding.tvSpicy2.setOnClickListener {
            tv_spicy2.isSelected = tv_spicy2.isSelected != true
        }
        binding.tvSpicy3.setOnClickListener {
            tv_spicy3.isSelected = tv_spicy3.isSelected != true
        }
        binding.tvSpicy4.setOnClickListener {
            tv_spicy4.isSelected = tv_spicy4.isSelected != true
        }
        binding.tvSpicy5.setOnClickListener {
            tv_spicy5.isSelected = tv_spicy5.isSelected != true
        }
    }
*/
}
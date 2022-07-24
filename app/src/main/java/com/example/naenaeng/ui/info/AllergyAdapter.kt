package com.example.naenaeng.ui.info

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.example.naenaeng.MyApplication.Companion.prefs
import com.example.naenaeng.databinding.AllergyItemViewBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ViewHolder(val binding: AllergyItemViewBinding)
    :RecyclerView.ViewHolder(binding.root)

class AllergyAdapter(val datas:MutableList<String>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private val db = Firebase.firestore
    private var allergyDatas = mutableListOf<String>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return ViewHolder(
            AllergyItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as ViewHolder).binding

        binding.tvAllergy.text = datas[position]

        //사용자별 알러지 DB에 저장
        binding.checkBox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener
        { buttonView, isChecked ->
            if(isChecked){
                allergyDatas.add(binding.tvAllergy.text.toString())
                db.collection("users").document(prefs.getString("email","null")).update("allergy",allergyDatas)
            }
            else {
                allergyDatas.remove(binding.tvAllergy.text.toString())
                db.collection("users").document(prefs.getString("email","null")).update("allergy",allergyDatas)
            }

        })
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    //override fun getItemCount(): Int = itemList.size
}
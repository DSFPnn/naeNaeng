package com.example.naenaeng.ui.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.naenaeng.MyApplication.Companion.prefs
import com.example.naenaeng.databinding.AllergyItemViewBinding
import com.example.naenaeng.model.Allergy
import com.example.naenaeng.model.AllergyList
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AllergyAdapter(itemList: MutableList<Allergy>)
    : RecyclerView.Adapter<AllergyAdapter.ViewHolder>(){
    private val db = Firebase.firestore
    private var allergyDatas = mutableListOf<String>()

    var itemList: MutableList<Allergy> = itemList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(itemViewBinding: AllergyItemViewBinding)
        :RecyclerView.ViewHolder(itemViewBinding.root){
        var allergy = itemViewBinding.tvAllergy
        var allergyCheck = itemViewBinding.checkBox
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllergyAdapter.ViewHolder {
        return ViewHolder(
            AllergyItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AllergyAdapter.ViewHolder, position: Int) {

        holder.allergy.text = itemList[position].type
        //holder.allergyCheck.isChecked = itemList[position].allergy_check==1

        //사용자별 알러지 DB에 저장
        holder.allergyCheck.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                allergyDatas.add(holder.allergy.text.toString())
                db.collection("users").document(prefs.getString("email", "null"))
                    .update("allergy", allergyDatas)
            } else {
                allergyDatas.remove(holder.allergy.text.toString())
                db.collection("users").document(prefs.getString("email", "null"))
                    .update("allergy", allergyDatas)
            }
        }
    }

    override fun getItemCount(): Int = itemList.size
}
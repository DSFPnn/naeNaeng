package com.example.naenaeng.ui.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.naenaeng.databinding.AllergyItemViewBinding
import com.example.naenaeng.model.Allergy

class AllergyAdapter(itemList: MutableList<Allergy>)
    : RecyclerView.Adapter<AllergyAdapter.ViewHolder>(){
    var itemList: MutableList<Allergy> = itemList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(itemViewBinding: AllergyItemViewBinding)
        :RecyclerView.ViewHolder(itemViewBinding.root){
        var allergy = itemViewBinding.tvAllergy
        //var allergyCheck = itemViewBinding.checkBox
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
    }

    override fun getItemCount(): Int = itemList.size
}
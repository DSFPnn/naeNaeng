package com.example.naenaeng.ui.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.naenaeng.databinding.AllergyItemViewBinding

class AllergyAdapter()
    : RecyclerView.Adapter<AllergyAdapter.ViewHolder>(){
    var itemList:ArrayList<String> = ArrayList()

    inner class ViewHolder(itemViewBinding: AllergyItemViewBinding)
        :RecyclerView.ViewHolder(itemViewBinding.root){
        var allergy = itemViewBinding.tvAllergy
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int =5
    //override fun getItemCount(): Int = itemList.size
}
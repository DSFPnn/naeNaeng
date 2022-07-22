package com.example.naenaeng.ui.info

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.example.naenaeng.databinding.AllergyItemViewBinding


class ViewHolder(val binding: AllergyItemViewBinding)
    :RecyclerView.ViewHolder(binding.root)

class AllergyAdapter(val datas:MutableList<String>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){


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

    }

    override fun getItemCount(): Int {
        return datas.size
    }

    //override fun getItemCount(): Int = itemList.size
}
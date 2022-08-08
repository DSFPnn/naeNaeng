package com.example.naenaeng.ui.recipe

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.naenaeng.MyApplication
import com.example.naenaeng.MyApplication.Companion.prefs
import com.example.naenaeng.databinding.PreferenceItemViewBinding
import com.example.naenaeng.model.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


class RecipeFilterAdatper(itemList: ArrayList<String>)
    : RecyclerView.Adapter<RecipeFilterAdatper.ViewHolder>(){

    var itemList: ArrayList<String> = itemList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(itemViewBinding: PreferenceItemViewBinding)
        :RecyclerView.ViewHolder(itemViewBinding.root){
        var preferenceCheck = itemViewBinding.tvPreference
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeFilterAdatper.ViewHolder {
        return ViewHolder(
            PreferenceItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecipeFilterAdatper.ViewHolder, position: Int) {

        val preference = prefs.getString("pref","null")

        holder.preferenceCheck.text = itemList[position]

        val item = holder.preferenceCheck.text
            if(preference.contains(item)){
                holder.preferenceCheck.isSelected = true
            }

        holder.preferenceCheck.setOnClickListener {
            holder.preferenceCheck.isSelected = !holder.preferenceCheck.isSelected
        }

    }

    override fun getItemCount(): Int = itemList.size
}
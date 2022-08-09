package com.example.naenaeng.ui.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.naenaeng.MyApplication
import com.example.naenaeng.databinding.PreferenceItemViewBinding


class PreferenceAdapter(itemList: ArrayList<String>)
    : RecyclerView.Adapter<PreferenceAdapter.ViewHolder>(){

    var preferenceDatas = mutableListOf<String>()
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
    ): PreferenceAdapter.ViewHolder {
        return ViewHolder(
            PreferenceItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PreferenceAdapter.ViewHolder, position: Int) {
        val preference = MyApplication.prefs.getString("pref","null")

        holder.preferenceCheck.text = itemList[position]

        val item = holder.preferenceCheck.text.toString()

        if(preference.contains(item)){
            holder.preferenceCheck.isSelected = true
            preferenceDatas.add(item)
        }

        // 사용자별 선호도 배열에 저장
        holder.preferenceCheck.setOnClickListener {
            if(holder.preferenceCheck.isSelected){
                preferenceDatas.remove(holder.preferenceCheck.text.toString())
                holder.preferenceCheck.isSelected = false
            } else{
                preferenceDatas.add(holder.preferenceCheck.text.toString())
                holder.preferenceCheck.isSelected = true
            }
        }
    }

    override fun getItemCount(): Int = itemList.size
}
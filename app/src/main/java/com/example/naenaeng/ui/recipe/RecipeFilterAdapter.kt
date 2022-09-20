package com.example.naenaeng.ui.recipe

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.RecyclerView
import com.example.naenaeng.databinding.PreferenceItemViewBinding
import com.example.naenaeng.model.Menu

class RecipeFilterAdapter (itemList: ArrayList<String>)
    : RecyclerView.Adapter<RecipeFilterAdapter.ViewHolder>(){

    var filterDatas = ArrayList<String>()
    var itemList: ArrayList<String> = itemList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(itemViewBinding: PreferenceItemViewBinding)
        : RecyclerView.ViewHolder(itemViewBinding.root){
        var filterCheck = itemViewBinding.tvPreference
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeFilterAdapter.ViewHolder {
        return ViewHolder(
            PreferenceItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecipeFilterAdapter.ViewHolder, position: Int) {
        holder.filterCheck.text = itemList[position]

        Log.d("filterArray adap",filterDatas.toString())
        if(filterDatas.contains(itemList[position])){
            holder.filterCheck.isSelected = true
            Log.d("filterArray check true","")
        }
        else{
            holder.filterCheck.isSelected = false
            Log.d("filterArray check false","")
        }
        // 사용자별 선호도 배열에 저장
        holder.filterCheck.setOnClickListener {
            if(holder.filterCheck.isSelected){
                filterDatas.remove(holder.filterCheck.text.toString())
                holder.filterCheck.isSelected = false
            } else{
                filterDatas.add(holder.filterCheck.text.toString())
                holder.filterCheck.isSelected = true
            }
            Log.d("adapp",filterDatas.toString())
        }
    }

    override fun getItemCount(): Int = itemList.size
}
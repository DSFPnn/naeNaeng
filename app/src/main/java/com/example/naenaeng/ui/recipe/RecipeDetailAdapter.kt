package com.example.naenaeng.ui.recipe

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.naenaeng.databinding.RecipeStepBinding

class RecipeDetailAdapter(itemList: ArrayList<String>)
    : RecyclerView.Adapter<RecipeDetailAdapter.ViewHolder>(){
    lateinit var context: Context

    var itemList: ArrayList<String> = itemList
        set(value) {
            field = value
            notifyDataSetChanged()
            Log.d("ingred itemlist",itemList.toString())
        }

    inner class ViewHolder(itemViewBinding: RecipeStepBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        val num: TextView = itemViewBinding.btnNum
        val step :TextView = itemViewBinding.textView13
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeDetailAdapter.ViewHolder {
        context = parent.context
        return ViewHolder(
            RecipeStepBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecipeDetailAdapter.ViewHolder, position: Int) {
        holder.step.text = itemList[position]
        holder.num.text = (position + 1).toString()
        Log.d("ingredd vh", itemList[position].toString())
    }
    override fun getItemCount(): Int = itemList.size
}
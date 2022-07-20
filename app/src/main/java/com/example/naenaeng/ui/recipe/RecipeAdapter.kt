package com.example.naenaeng.ui.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.naenaeng.databinding.RecipeItemViewBinding

class RecipeAdapter :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {
    /*
    var itemList: List<TimetableAPIModel> = itemList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

     */

    inner class ViewHolder(itemViewBinding: RecipeItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        val recipe: ImageView = itemViewBinding.recipePicture
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeAdapter.ViewHolder {
        return ViewHolder(
            RecipeItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecipeAdapter.ViewHolder, position: Int) {
       holder.recipe.clipToOutline = true
    }

    override fun getItemCount(): Int =5
    //override fun getItemCount(): Int = itemList.size

}
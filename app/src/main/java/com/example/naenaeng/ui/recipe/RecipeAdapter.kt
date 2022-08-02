package com.example.naenaeng.ui.recipe

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.naenaeng.databinding.RecipeItemViewBinding
import com.example.naenaeng.model.Menu
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RecipeAdapter(itemList: ArrayList<Menu>)
: RecyclerView.Adapter<RecipeAdapter.ViewHolder>(){
    private val db = Firebase.firestore
    private var ingredientDatas = mutableListOf<String>()

    var itemList: ArrayList<Menu> = itemList
    set(value) {
        field = value
        notifyDataSetChanged()
        Log.d("ingred itemlist",itemList.toString())
    }

    inner class ViewHolder(itemViewBinding: RecipeItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        val recipeImg: ImageView = itemViewBinding.recipePicture
        val title: TextView = itemViewBinding.tvTitle
        val index: TextView = itemViewBinding.tvRecipeIndex
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
        holder.recipeImg.clipToOutline = true

        holder.title.text = itemList[position].title
        holder.index.text = itemList[position].index
        //Log.d("ingredd vh",itemList[position].toString())
    }

    override fun getItemCount(): Int = itemList.size
}
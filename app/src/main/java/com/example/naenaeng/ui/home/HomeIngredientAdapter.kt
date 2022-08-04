package com.example.naenaeng.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.naenaeng.MyApplication.Companion.prefs
import com.example.naenaeng.databinding.IngredientItemViewBinding
import com.example.naenaeng.model.Ingredient
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeIngredientAdapter(itemList: ArrayList<Ingredient>)
: RecyclerView.Adapter<HomeIngredientAdapter.ViewHolder>(){
    private val db = Firebase.firestore
    private var ingredientDatas = mutableListOf<String>()

    var itemList: ArrayList<Ingredient> = itemList
    set(value) {
        field = value
        notifyDataSetChanged()
        Log.d("ingred itemlist",itemList.toString())
    }

    inner class ViewHolder(itemViewBinding: IngredientItemViewBinding)
        :RecyclerView.ViewHolder(itemViewBinding.root){
        val ingredientImg = itemViewBinding.imgIngredient
        val firstLetter = itemViewBinding.tvFirstLetter
        val name = itemViewBinding.tvIngredientName
        val life = itemViewBinding.tvIngredientDate
        val remove = itemViewBinding.btnRemove
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeIngredientAdapter.ViewHolder {
        return ViewHolder(
            IngredientItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeIngredientAdapter.ViewHolder, position: Int) {
        holder.firstLetter.text = itemList[position].name.substring(0,1)
        holder.ingredientImg.background
        holder.name.text = itemList[position].name
        holder.life.text = itemList[position].date
        Log.d("ingredd vh",itemList[position].toString())
        //holder.allergyCheck.isChecked = itemList[position].allergy_check==1

        //삭제 버튼 클릭시 재료 DB에서 삭제
        holder.remove.setOnClickListener {
            db.collection("users").document(prefs.getString("email", "null"))
                .update("ingredients", FieldValue.arrayRemove(itemList[position]))
            itemList.removeAt(position)
            notifyItemRemoved(position)
        }


    }

    override fun getItemCount(): Int = itemList.size
}


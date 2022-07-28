package com.example.naenaeng.ui.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.naenaeng.MyApplication
import com.example.naenaeng.databinding.AllergyItemViewBinding
import com.example.naenaeng.databinding.IngredientItemViewBinding
import com.example.naenaeng.model.Ingredient
import com.example.naenaeng.ui.info.AllergyAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/*
class ingredientViewHolder(val binding: IngredientItemViewBinding )
    : RecyclerView.ViewHolder(binding.root)

class HomeIngredientAdapter(val datas: ArrayList<HashMap<String, String>>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private val db = Firebase.firestore

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return ingredientViewHolder(
            IngredientItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as ingredientViewHolder).binding

        binding.textView2.text = datas[position].get("Name").toString()
        binding.textView3.text = "유통기한 : " + datas[position].get("date").toString()

        binding.btnRemove.setOnClickListener {
           // db.collection("users").document(prefs.getString("email","null"))
             //   .update("ingredients", FieldValue.arrayRemove(datas[position]))

        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }

}

 */
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
        var name = itemViewBinding.tvIngredientName
        var life = itemViewBinding.tvIngredientDate
        var remove = itemViewBinding.btnRemove
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
        holder.name.text = itemList[position].name
        holder.life.text = itemList[position].date
        Log.d("ingredd vh",itemList[position].toString())
        //holder.allergyCheck.isChecked = itemList[position].allergy_check==1

    }

    override fun getItemCount(): Int = itemList.size
}
package com.example.naenaeng.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.naenaeng.databinding.IngredientItemViewBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


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
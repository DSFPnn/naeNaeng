package com.example.naenaeng.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.naenaeng.MyApplication.Companion.prefs
import com.example.naenaeng.databinding.IngredientItemViewBinding
import com.example.naenaeng.model.Ingredient
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.absoluteValue

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
        val warning = itemViewBinding.tvWarning
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

        //유통기한 확인
        val today = Date().time
        val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일")
        val itemDate = dateFormat.parse(holder.life.text.toString()).time
        var dDay = (itemDate - today) / (24 * 60 * 60 * 1000)
        if((dDay<7) and (dDay>0)) {
            holder.warning.setText("유통기한이 ${dDay}일 남았습니다!")
            holder.warning.visibility = View.VISIBLE
        }
        else if (dDay.toInt() == 0){
            holder.warning.setText("유통기한이 오늘까지입니다!")
            holder.warning.visibility = View.VISIBLE
        }
        else if (dDay<0) {
            dDay = dDay.absoluteValue
            holder.warning.setText("유통기한이 ${dDay}일 지났습니다!")
            holder.warning.visibility = View.VISIBLE
        }

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


package com.example.naenaeng.ui.recipe

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
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
        val layout : LinearLayout = itemViewBinding.recipeItemLayout
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

        // (1) 리스트 내 항목 클릭 시 onClick() 호출
        holder.layout.setOnClickListener {
            Log.d("clickk adapter","눌림")
            val status = "edit"
            itemClickListener?.onClick(it, position, status)
        }
    }
    // (2) 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int, m:String)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    // (4) setItemClickListener로 설정한 함수 실행
    private var itemClickListener : OnItemClickListener? = null

    override fun getItemCount(): Int = itemList.size
}
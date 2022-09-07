package com.example.naenaeng.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.RecyclerView
import com.example.naenaeng.MyApplication.Companion.prefs
import com.example.naenaeng.R
import com.example.naenaeng.databinding.IngredientItemViewBinding
import com.example.naenaeng.model.Ingredient
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeIngredientAdapter(itemList: ArrayList<Ingredient>, editMode: Boolean, fragmentManager: FragmentManager)
: RecyclerView.Adapter<HomeIngredientAdapter.ViewHolder>(){
    private val db = Firebase.firestore
    private var ingredientDatas = mutableListOf<String>()
    lateinit var context:Context
    private val editMode = editMode
    private var mFragmentManager : FragmentManager
    init {
        mFragmentManager = fragmentManager
    }

    var itemList: ArrayList<Ingredient> = itemList
    set(value) {
        field = value
        notifyDataSetChanged()
        Log.d("ingred itemlist",itemList.toString())
    }

    inner class ViewHolder(itemViewBinding: IngredientItemViewBinding)
        :RecyclerView.ViewHolder(itemViewBinding.root){
        val itemVieww = itemViewBinding.layoutIngredient
        val ingredientImg = itemViewBinding.imgIngredient
        val firstLetter = itemViewBinding.tvFirstLetter
        val name = itemViewBinding.tvIngredientName
        val date = itemViewBinding.tvIngredientDate
        val added = itemViewBinding.tvIngredientAdded
        val remove = itemViewBinding.btnRemove
        val edit = itemViewBinding.btnEditDialog
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HomeIngredientAdapter.ViewHolder {
        context = parent.context
        return ViewHolder(
            IngredientItemViewBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeIngredientAdapter.ViewHolder, position: Int) {
        holder.firstLetter.text = itemList[position].name.substring(0,1)
        holder.ingredientImg.background
        holder.name.text = itemList[position].name
        holder.date.text = itemList[position].date
        Log.d("ingredd vh",itemList[position].toString())
        //holder.allergyCheck.isChecked = itemList[position].allergy_check==1

        if(editMode){ // editMode==true:수정, editMode==false:일반
            holder.remove.visibility= View.VISIBLE
            holder.edit.visibility = View.VISIBLE
            holder.date.background = ContextCompat.getDrawable(context, R.drawable.bottom_line)
        }
        else{
            holder.remove.visibility= View.INVISIBLE
            holder.edit.visibility = View.INVISIBLE
            holder.date.background = ContextCompat.getDrawable(context, R.color.white)
        }

        // (1) 리스트 내 항목 클릭 시 onClick() 호출
        holder.edit.setOnClickListener {
            Log.d("clickk adapter","눌림")
            val status = "edit"
            itemClickListener?.onClick(it, position, status)
        }
        holder.remove.setOnClickListener {
            Log.d("clickk removeBtn", "눌림")
            val status = "remove"
            itemClickListener?.onClick(it, position, status)
        }

//        // 수정 모드에서 유통기한 클릭시 유통기한 수정
//        holder.date.setOnClickListener {
//            IngredientDateDialog().show(mFragmentManager,"date")
//
//            var map= mutableMapOf<String,Any>()
//            map[holder.name.text.toString()] = date
//            db.collection("users").document(prefs.getString("email","null"))
//                .update(map)
//                .addOnCompleteListener {
//                    if(it.isSuccessful){
//                        Toast.makeText(context, "수정 완료", Toast.LENGTH_SHORT).show()
//                    }
//                }
//        }
//        //삭제 버튼 클릭시 재료 DB에서 삭제
//        holder.remove.setOnClickListener {
//            db.collection("users").document(prefs.getString("email","null"))
//              .update("ingredients", FieldValue.arrayRemove(itemList[position]))
//            itemList.removeAt(position)
//            notifyItemRemoved(position)
//        }
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
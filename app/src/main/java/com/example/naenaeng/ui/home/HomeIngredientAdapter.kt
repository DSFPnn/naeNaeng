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
import com.bumptech.glide.Glide
import com.example.naenaeng.MyApplication.Companion.prefs
import com.example.naenaeng.R
import com.example.naenaeng.databinding.IngredientItemViewBinding
import com.example.naenaeng.model.Ingredient
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.absoluteValue

class HomeIngredientAdapter(itemList: ArrayList<Ingredient>, editMode: Boolean, fragmentManager: FragmentManager)
: RecyclerView.Adapter<HomeIngredientAdapter.ViewHolder>(){
    private val db = Firebase.firestore
    private var ingredientDatas = mutableListOf<String>()
    lateinit var context:Context
    private val editMode = editMode
    private var mFragmentManager : FragmentManager
    private val storage: FirebaseStorage = FirebaseStorage.getInstance("gs://naenaeng-bebed.appspot.com")
    private val storageRef: StorageReference = storage.reference

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
        val ingredientImg = itemViewBinding.imgIngredient
        val firstLetter = itemViewBinding.tvFirstLetter
        val name = itemViewBinding.tvIngredientName
        val date = itemViewBinding.tvIngredientDate
        val remove = itemViewBinding.btnRemove
        val warning = itemViewBinding.tvWarning
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
        if(itemList[position].imageInt != -1)
            holder.ingredientImg.setImageResource(itemList[position].imageInt)
        else
            holder.ingredientImg.setImageResource(R.drawable.gray_solid_radius8)
        holder.ingredientImg.clipToOutline = true

        holder.name.text = itemList[position].name
        holder.name.isSelected = true
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

        //유통기한 확인
        val currentTime = System.currentTimeMillis()
        val dataFormat = SimpleDateFormat("yyyyMMdd")
        val today = dataFormat.format(currentTime).toInt()
        val itemDate = (itemList[position].date.substring(0..3) + itemList[position].date.substring(6..7)
                + itemList[position].date.substring(10..11)).toInt()
        val dDay = itemDate - today

        if((dDay<7) and (dDay>0)) {
            Log.d("ddayy a",dDay.toString())
            holder.warning.text = "유통기한이 ${dDay}일 남았습니다!"
            holder.warning.visibility = View.VISIBLE
        }
        else if (dDay.toInt() == 0){
            Log.d("ddayy b",dDay.toString())
            holder.warning.text = "유통기한이 오늘까지입니다!"
            holder.warning.visibility = View.VISIBLE
        }
        else if (dDay<0) {
            Log.d("ddayy c",dDay.toString())
            val abDDay=dDay.absoluteValue
            holder.warning.text = "유통기한이 ${abDDay}일 지났습니다!"
            holder.warning.visibility = View.VISIBLE
        }
        else{
            holder.warning.text = ""
            holder.warning.visibility = View.INVISIBLE
        }

        // (1) 리스트 내 항목 클릭 시 onClick() 호출
        holder.edit.setOnClickListener {
            Log.d("clickk adapter","눌림")
            val status = "edit"
            itemClickListener?.onClick(it, position, status)
        }
        holder.remove.setOnClickListener {
            val status = "remove"
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
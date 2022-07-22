package com.example.naenaeng.ui.info

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.example.naenaeng.MyApplication.Companion.prefs
import com.example.naenaeng.databinding.AllergyItemViewBinding
import com.google.firebase.database.DatabaseReference


class ViewHolder(val binding: AllergyItemViewBinding)
    :RecyclerView.ViewHolder(binding.root)

class AllergyAdapter(val datas:MutableList<String>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private lateinit var database: DatabaseReference
    private var i : Int = 1
    private var num = prefs.getString("num","0")

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return ViewHolder(
            AllergyItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as ViewHolder).binding


        binding.tvAllergy.text = datas[position]


        binding.checkBox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener
        { buttonView, isChecked ->
            if(isChecked){
               //database.child("users").child("user${num}").child("allergy").child("$i").setValue(binding.tvAllergy.text)
                i = i + 1
            }
            else {
               //database.child("users").child("user${num}").child("allergy").child("$i").removeValue()
                i = i - 1
            }

        })
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    //override fun getItemCount(): Int = itemList.size
}
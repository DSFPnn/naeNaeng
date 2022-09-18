package com.example.naenaeng.ui.home

import android.widget.CalendarView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.naenaeng.MyApplication
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseDialogFragment
import com.example.naenaeng.databinding.DialogIngredientDateBinding
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class IngredientDateDialog : BaseDialogFragment<DialogIngredientDateBinding>(R.layout.dialog_ingredient_date){
    private lateinit var date :String

    override fun initAfterBinding() {
        super.initAfterBinding()

        // 캘린더 날짜 가져오기
        binding.calendarView.setOnDateChangeListener { _: CalendarView, year: Int, month: Int, dayOfMonth: Int ->
            val _month = if((month+1)<10) "0${month + 1}" else "${month + 1}"
            val _dayOfMonth = if(dayOfMonth<10) "0${dayOfMonth}" else "${dayOfMonth}"

            date = "${year}년 ${_month}월 ${_dayOfMonth}일"

        }
        binding.btnSetLife.setOnClickListener{
            setFragmentResult("requestDate", bundleOf("date" to date))
            dismiss()
        }

        binding.btnSetLife.setOnClickListener{
            //날짜 미선택시 오늘날짜 가져오기
            try{
                setFragmentResult("requestDate", bundleOf("date" to date))
            }catch(e: UninitializedPropertyAccessException){
                val now = Date()
                val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일")
                date= dateFormat.format(now)
                setFragmentResult("requestDate", bundleOf("date" to date))
            }
            dismiss()
        }
    }
}
package com.example.naenaeng.ui.home

import android.widget.CalendarView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseDialogFragment
import com.example.naenaeng.databinding.DialogIngredientDateBinding
import java.text.SimpleDateFormat
import java.util.*


class IngredientLifeDialog : BaseDialogFragment<DialogIngredientDateBinding>(R.layout.dialog_ingredient_date){
    private lateinit var date :String
    override fun initAfterBinding() {
        super.initAfterBinding()

        //캘린더 날짜 가져오기
        binding.calendarView.setOnDateChangeListener(CalendarView.OnDateChangeListener() {
                calendarView: CalendarView, year: Int, month: Int, dayOfMonth: Int ->
            date = "${year}년 ${month+1}월 ${dayOfMonth}일"

        })

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
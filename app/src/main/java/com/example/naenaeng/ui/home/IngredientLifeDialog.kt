package com.example.naenaeng.ui.home

import android.widget.CalendarView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseDialogFragment
import com.example.naenaeng.databinding.DialogIngredientLifeBinding


class IngredientLifeDialog : BaseDialogFragment<DialogIngredientLifeBinding>(R.layout.dialog_ingredient_life){
    private lateinit var date :String

    override fun initAfterBinding() {
        super.initAfterBinding()

        //캘린더 날짜 가져오기
        binding.calendarView.setOnDateChangeListener(CalendarView.OnDateChangeListener() {
                calendarView: CalendarView, year: Int, month: Int, dayOfMonth: Int ->
            date = "${year}년 ${month+1}월 ${dayOfMonth}일"
            setFragmentResult("requestDate", bundleOf("date" to date))

        })

        binding.btnSetLife.setOnClickListener{
            dismiss()
        }
    }

}
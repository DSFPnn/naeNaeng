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
import java.util.*
import kotlin.collections.ArrayList


class IngredientDateDialog : BaseDialogFragment<DialogIngredientDateBinding>(R.layout.dialog_ingredient_date){
    private val db = Firebase.firestore
    private lateinit var date :String

    override fun initDataBinding() {
        super.initDataBinding()
    }
    override fun initAfterBinding() {
        super.initAfterBinding()

        val cal = Calendar.getInstance()
        // 캘린더 날짜 가져오기
        binding.calendarView.setOnDateChangeListener { _: CalendarView, year: Int, month: Int, dayOfMonth: Int ->
            date = "${year}년 ${month + 1}월 ${dayOfMonth}일"
        }
        binding.btnSetLife.setOnClickListener{
            setFragmentResult("requestDate", bundleOf("date" to date))
            dismiss()
        }
    }

}
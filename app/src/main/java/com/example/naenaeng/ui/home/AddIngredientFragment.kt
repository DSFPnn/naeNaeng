package com.example.naenaeng.ui.home

import android.util.Log
import androidx.fragment.app.setFragmentResultListener
import com.example.naenaeng.MainActivity
import com.example.naenaeng.MyApplication.Companion.prefs
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentAddIngredientBinding
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat

class AddIngredientFragment: BaseFragment<FragmentAddIngredientBinding>(R.layout.fragment_add_ingredient) {//음식 추가 화면
    private val db = Firebase.firestore
    private val ingredRef = db.collection("public")
    private var imageClass: String = "null jpg"

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).setToolbarTitle("재료 추가")
    }

    override fun initDataBinding() {
        super.initDataBinding()
        binding.imgAddIngredient.clipToOutline = true

        //재료이름 받아오기
        setFragmentResultListener("requestIngredient") { _, bundle ->
            val result = bundle.getString("ingredient")
            binding.btnIngredientName.text=result
        }

        //유통기한 받아오기
        setFragmentResultListener("requestDate") { _, bundle ->
            val result = bundle.getString("date")
            binding.btnIngredientDate.text=result
        }
    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnIngredientName.setOnClickListener {
            IngredientNameDialog().show(parentFragmentManager, "preference")
        }
        binding.btnIngredientDate.setOnClickListener {
            IngredientDateDialog().show(parentFragmentManager, "preference")
        }
        binding.btnSetIngredient.setOnClickListener {
            //재료 분류 검색
            ingredRef.document("ingredient").get().addOnSuccessListener {
                val ingred = it.data
                val ingredType = ingred?.get("ingredType") as HashMap<String, ArrayList<String>>
                val typeList = ingred?.get("typeName") as ArrayList<String>
                for (type in typeList) {
                    val typeArray = ingredType?.get(type)
                    if (typeArray != null) {
                        if (binding.btnIngredientName.text in typeArray) {
                            imageClass = type

                            val data = hashMapOf(
                                "name" to binding.btnIngredientName.text,
                                "date" to binding.btnIngredientDate.text,
                                "added" to today(),
                                "imageClass" to imageClass
                            )

                            //firestore에 재료추가
                            db.collection("users").document(prefs.getString("email", "null"))
                                .update("ingredients", FieldValue.arrayUnion(data))

                        }
                    }
                }
            }
            navController.navigate(R.id.action_addIngredientFragment_to_homeFragment)
         }
    }

    private fun today(): String {
        val currentTime = System.currentTimeMillis()
        val dataFormat = SimpleDateFormat("yyyyMMdd")

        return dataFormat.format(currentTime).toString()
    }
}
package com.example.naenaeng.ui.home

import androidx.fragment.app.setFragmentResultListener
import com.example.naenaeng.MainActivity
import com.example.naenaeng.MyApplication.Companion.prefs
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentAddIngredientBinding
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddIngredientFragment: BaseFragment<FragmentAddIngredientBinding>(R.layout.fragment_add_ingredient) {//음식 추가 화면
    //private lateinit var navController : NavController
    private val db = Firebase.firestore

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).setToolbar("재료 추가")
    }

    override fun initDataBinding() {
        super.initDataBinding()
        binding.imgIngredient.clipToOutline = true

        //재료이름 받아오기
        setFragmentResultListener("requestIngredient") { requestKey, bundle ->
            val result = bundle.getString("ingredient")
            binding.btnIngredientName.text=result
        }

        //유통기한 받아오기
        setFragmentResultListener("requestDate") { requestKey, bundle ->
            val result = bundle.getString("date")
            binding.btnIngredientLife.text=result
        }

    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnIngredientName.setOnClickListener {
            navController.navigate(R.id.action_addIngredientFragment_to_ingredientNameFragment)
        }
        binding.btnIngredientLife.setOnClickListener {
            navController.navigate(R.id.action_addIngredientFragment_to_ingredientLifeFragment)
        }
        binding.btnSetIngredient.setOnClickListener{
            navController.navigate(R.id.action_addIngredientFragment_to_homeFragment)

            val data =  hashMapOf(
                "Name" to binding.btnIngredientName.text,
                "date" to binding.btnIngredientLife.text
            )

            //firestore에 재료추가
            db.collection("users").document(prefs.getString("email","null"))
                .update("ingredients", FieldValue.arrayUnion(data))

        }
    }

}
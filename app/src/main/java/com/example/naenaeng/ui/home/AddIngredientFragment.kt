package com.example.naenaeng.ui.home

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import com.example.naenaeng.MainActivity
import com.example.naenaeng.MyApplication
import com.example.naenaeng.MyApplication.Companion.ingredientIamgeHash
import com.example.naenaeng.MyApplication.Companion.prefs
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentAddIngredientBinding
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat

class AddIngredientFragment: BaseFragment<FragmentAddIngredientBinding>(R.layout.fragment_add_ingredient) { //음식 추가 화면
    val db = Firebase.firestore
    private val ingredRef = db.collection("public")
    private var imageInt = "-1"

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).setToolbarTitle("재료 추가")
    }

    override fun initDataBinding() {
        super.initDataBinding()

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
            IngredientNameDialog().show(parentFragmentManager, "ingred_name")
        }
        binding.btnIngredientDate.setOnClickListener {
            IngredientDateDialog().show(parentFragmentManager, "ingred_date")
        }
        binding.btnSetIngredient.setOnClickListener {
            if(binding.btnIngredientName.text=="" || binding.btnIngredientDate.text==""){
                Toast.makeText(context, "재료를 입력해주세요!", Toast.LENGTH_SHORT).show()
            }
            else{
                var imageClass:String = "nullJpg"
                // 이미지 추가
                ingredRef.document("ingredient").get().addOnSuccessListener {
                    val ingred = it.data
                    val ingredType = ingred?.get("ingredType") as HashMap<String, ArrayList<String>> // {mushrooms=[느타리버섯, 능이버섯,...], seafoods=[...],...}
                    val typeList = ingred?.get("typeName") as ArrayList<String> // [fruit, bean, egg, grain, milk ...]
                    var itzy:Boolean = false
                    lateinit var data:HashMap<String,CharSequence>

                    for (type in typeList) { // 재료 이름이 이 안에 있는 지 검사
                        Log.d("meattType",type.toString())
                        val typeArray = ingredType?.get(type) // [대추, 단감, ...] [콩, 팥] ...
                        if (typeArray != null) {
                            if (binding.btnIngredientName.text in typeArray) {
                                imageClass = type
                                imageInt = ingredientIamgeHash[type].toString()
                                data = hashMapOf(
                                    "name" to binding.btnIngredientName.text,
                                    "date" to binding.btnIngredientDate.text,
                                    "added" to today(),
                                    "imageClass" to imageClass,
                                    "imageInt" to imageInt
                                )
                                Log.d("meattArray",typeArray.toString())
                                Log.d("meatt",data.toString())
                                itzy = true
                                break
                            }
                        }
                    }
                    if(!itzy){
                        data = hashMapOf(
                            "name" to binding.btnIngredientName.text,
                            "date" to binding.btnIngredientDate.text,
                            "added" to today(),
                            "imageClass" to imageClass,
                            "imageInt" to imageInt
                        )
                    }
                    //firestore에 재료추가
                    db.collection("users").document(prefs.getString("email", "null"))
                        .update("ingredients", FieldValue.arrayUnion(data)).addOnSuccessListener {
                            navController.navigate(R.id.action_addIngredientFragment_to_homeFragment)
                        }
                }
            }
         }
    }

    private fun today(): String {
        val currentTime = System.currentTimeMillis()

        return currentTime.toString()
    }

}
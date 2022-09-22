package com.example.naenaeng.ui.recipe

import android.content.ContentValues
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.naenaeng.MyApplication
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseBottomDialogFragment
import com.example.naenaeng.base.BaseDialogFragment
import com.example.naenaeng.databinding.DialogRecipeDetailBinding
import com.example.naenaeng.model.Menu
import com.example.naenaeng.model.User
import com.example.naenaeng.repository.UserRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class RecipeDetailDialog(detail:Menu) : BaseBottomDialogFragment<DialogRecipeDetailBinding>(R.layout.dialog_recipe_detail) {
    val db = Firebase.firestore
    private val recipe = detail
    private lateinit var recipeDetailAdapter:RecipeDetailAdapter
    private lateinit var userRepository: UserRepository

    override fun initDataBinding() {
        super.initDataBinding()

        val stepArray = recipe.step.split("\\")
        var step:String = ""
        for(i in stepArray.indices){
            step += ((i+1).toString() + ". " + stepArray[i] + "\n\n")
        }

        val presentIngredient:ArrayList<String> = ArrayList()
        db.collection("users").document(MyApplication.prefs.getString("email","null")).get()
            .addOnSuccessListener { documentSnapshot ->
                val data = documentSnapshot.toObject<User>()
                if (data != null) {
                    Log.d("abcc data","not null")
                    Log.d("abcc ingred",data.ingredients.size.toString() + data.ingredients.toString())
                    for(i in 0 until data.ingredients.size){
                        presentIngredient.add(data.ingredients[i].name)
                        Log.d("abcc in name",data.ingredients[i].toString())
                        Log.d("abcc name",data.ingredients[i].name)
                        Log.d("abcc 그래서 어레이",presentIngredient.toString())
                    }

                }else{
                    Log.d("abcc data","null")
                }
                Log.d("abcc 2", presentIngredient.toString())
                val inRefregerator:ArrayList<String> = ArrayList()
                val notInRefregerator:ArrayList<String> = ArrayList()
                val ingredient:ArrayList<String> = recipe.ingredients
                for(i in 0 until ingredient.size){
                    Log.d("abcc 뭔데 어디있는데", ingredient[i].toString())
                    if(presentIngredient.contains(ingredient[i])){
                        inRefregerator.add(ingredient[i])
                    }
                    else{
                        notInRefregerator.add(ingredient[i])
                    }
                }

                Log.d("abcc 인",inRefregerator.toString())
                Log.d("abcc 낫인",notInRefregerator.toString())
                binding.tvRecipeTitle.text = recipe.title
                binding.tvInRefregerator.text = if(inRefregerator.size == 0 )
                    "" else inRefregerator.toString().substring(1,inRefregerator.toString().length-1)
                binding.tvNotInRefrigerator.text = if(notInRefregerator.size == 0 )
                    "" else notInRefregerator.toString().substring(1,notInRefregerator.toString().length-1)
                binding.tvIngred.text = recipe.essentialIngredients
                binding.tvIngred2.text = recipe.additionalIngredients

                if(binding.tvInRefregerator.text == ""){
                    binding.tvInRefregerator.visibility = View.GONE
                } else{
                    binding.tvInRefregerator.visibility = View.VISIBLE
                }
                if(binding.tvNotInRefrigerator.text == "") {
                    binding.tvNotInRefrigerator.visibility = View.GONE
                } else{
                    binding.tvNotInRefrigerator.visibility = View.VISIBLE
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }

        Log.d("abcc 프리퍼런스가 먼데", presentIngredient.toString())
        // 냉장고에 있는 재료인지 확인
//        userRepository = UserRepository()
        //val presentIngredientsArray = UserRepository().getData().value?.ingredients?: ArrayList() // 사용자 냉장고 재료 객체
//        Log.d("abcc 0", UserRepository().getData().value.toString())
//        Log.d("abcc 1", presentIngredientsArray.toString())
//        val presentIngredient:ArrayList<String> = ArrayList()
//        if(presentIngredientsArray!=null){
//            for(element in presentIngredientsArray){
//                presentIngredient.add(element.name) // 사용자 냉장고에 있는 재료의 이름 배열
//            }
//        }


        recipeDetailAdapter= RecipeDetailAdapter(stepArray as ArrayList<String>)
        binding.recyclerVeiw.adapter=recipeDetailAdapter

        binding.btnRecipeClose.setOnClickListener {
            dismiss()
        }
        binding.imageView4.clipToOutline = true
        binding.imageView4.setImageResource(recipe.imageInt)
    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnRecipeClose.setOnClickListener {
            dismiss()
        }
    }
}
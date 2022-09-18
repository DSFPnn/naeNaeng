package com.example.naenaeng.ui.recipe

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.naenaeng.R
import com.example.naenaeng.databinding.DialogRecipeDetailBinding
import com.example.naenaeng.model.Menu
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class RecipeDetailDialog(detail:Menu) : DialogFragment(R.layout.dialog_recipe_detail) {
    private val recipe = detail
    private val storage: FirebaseStorage = FirebaseStorage.getInstance("gs://naenaeng-bebed.appspot.com")
    private val storageRef: StorageReference = storage.reference
    private var _binding: DialogRecipeDetailBinding? = null
    private lateinit var recipeDetailAdapter:RecipeDetailAdapter
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogRecipeDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        // 레이아웃 배경을 투명하게 해줌, 필수 아님
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val stepArray = recipe.step.split("\\")
        var step:String = ""
        for(i in stepArray.indices){
            step += ((i+1).toString() + ". " + stepArray[i] + "\n\n")
        }
        binding.tvRecipeTitle.text = recipe.title
        binding.tvIngred.text = recipe.essentialIngredients
        binding.tvIngred2.text = recipe.additionalIngredients

        recipeDetailAdapter= RecipeDetailAdapter(stepArray as ArrayList<String>)
        binding.recyclerVeiw.adapter=recipeDetailAdapter

        binding.btnRecipeClose.setOnClickListener {
            dismiss()
        }

        storageRef.child("recipe/"+recipe.imageName).downloadUrl.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Glide.with(requireContext())
                    .load(task.result)
                    .fitCenter()
                    .into(binding.imageView4)
            }
        }
        Log.d("menuu",recipe.toString())

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
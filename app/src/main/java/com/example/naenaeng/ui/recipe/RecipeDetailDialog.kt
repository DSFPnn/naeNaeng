package com.example.naenaeng.ui.recipe

import android.util.Log
import com.bumptech.glide.Glide
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseBottomDialogFragment
import com.example.naenaeng.databinding.DialogRecipeDetailBinding
import com.example.naenaeng.model.Menu
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class RecipeDetailDialog(detail:Menu) : BaseBottomDialogFragment<DialogRecipeDetailBinding>(R.layout.dialog_recipe_detail) {
    private val recipe = detail
    private val storage: FirebaseStorage = FirebaseStorage.getInstance("gs://naenaeng-bebed.appspot.com")
    private val storageRef: StorageReference = storage.reference

    override fun initDataBinding() {
        super.initDataBinding()

        val stepArray = recipe.step.split("\\")
        var step:String = ""
        for(i in stepArray.indices){
            step += ((i+1).toString() + ". " + stepArray[i] + "\n\n")
        }
        binding.tvTitleDetail.text = recipe.title
        binding.tvAdditionalIngredient.text = recipe.additionalIngredients
        binding.tvEssentialIngredient.text = recipe.essentialIngredients
        binding.tvStep.text = step

        storageRef.child("recipe/"+recipe.imageName).downloadUrl.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Glide.with(requireContext())
                    .load(task.result)
                    .fitCenter()
                    .into(binding.recipePicture)
            }
        }
        Log.d("menuu",recipe.toString())
    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }
}
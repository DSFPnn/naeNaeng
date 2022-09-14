package com.example.naenaeng.ui.recipe

import android.util.Log
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseBottomDialogFragment
import com.example.naenaeng.databinding.DialogRecipeDetailBinding
import com.example.naenaeng.model.Menu

class RecipeDetailDialog(detail:Menu) : BaseBottomDialogFragment<DialogRecipeDetailBinding>(R.layout.dialog_recipe_detail) {
    private val recipe = detail

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
        Log.d("menuu",recipe.toString())
    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }
}
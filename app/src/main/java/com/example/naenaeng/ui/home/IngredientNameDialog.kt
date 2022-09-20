package com.example.naenaeng.ui.home

import android.app.AlertDialog
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseDialogFragment
import com.example.naenaeng.databinding.DialogIngredientNameBinding


class IngredientNameDialog :BaseDialogFragment<DialogIngredientNameBinding>(R.layout.dialog_ingredient_name){

    override fun initAfterBinding() {
        super.initAfterBinding()
        AlertDialog.Builder(context)

        binding.btnSetName.setOnClickListener{
            val ingredient = binding.etId.text.toString()
            if(ingredient.isEmpty()) {
                Toast.makeText(context, "재료를 입력해주세요!", Toast.LENGTH_SHORT).show()
            }
            else{
                setFragmentResult("requestIngredient", bundleOf("ingredient" to ingredient))
                dismiss()
            }
        }
        binding.btnCloseName.setOnClickListener{
            dismiss()
        }
    }


}
package com.example.naenaeng.ui.home

import android.animation.ObjectAnimator
import android.content.Intent
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.example.naenaeng.MainActivity
import com.example.naenaeng.MyApplication
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentHomeBinding
import com.example.naenaeng.model.Ingredient
import com.example.naenaeng.viewmodel.UserViewModel
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class HomeFragment:BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var homeIngredientAdapter:HomeIngredientAdapter
    private var isFabOpen = false // Fab 버튼 default는 닫혀있음
    private var editMode = false // ture:수정모드 false:일반모드
    val db = Firebase.firestore
    private lateinit var result:String

    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).setToolbarTitle("none")
    }
    private val viewModel by lazy {
        ViewModelProvider(this).get(UserViewModel::class.java)
    }

    override fun initDataBinding() {
        super.initDataBinding()

        homeIngredientAdapter = HomeIngredientAdapter(ArrayList(), editMode, parentFragmentManager)
        binding.ingredientRecyclerView.adapter=homeIngredientAdapter
        viewModel.getUserIngredient()
        viewModel.userIngredientLiveData.observe(viewLifecycleOwner) { itemList ->
            homeIngredientAdapter.itemList = itemList
            Log.d("ingredd", "1"+itemList.toString())
        }

        // 재료 추가 모드
        setFragmentResultListener("requestAddMode") { _, bundle ->
            result = bundle.getString("addMode").toString()

            if (result == "manual")
                navController.navigate(R.id.action_homeFragment_to_addIngredientFragment)
            else if (result == "automatic")
                (activity as MainActivity).replaceToCameraActivity()
            result=""
        }
    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnEtc.setOnClickListener {
            toggleFab()
        }
        binding.btnAddIngredient.setOnClickListener {
            SelectAddModeDialog().show(parentFragmentManager, "select_add_mode")
        }

        binding.btnEditIngredient.setOnClickListener {
            selectMode(true)
        }

        binding.btnCheck.setOnClickListener {
            selectMode(false)
        }
        //검색기능
        binding.etSearchIngredient.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val searchString = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // TODO("not implemented")
                // To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchItemList: ArrayList<Ingredient> = ArrayList<Ingredient>()
                val searchString = binding.etSearchIngredient.text

                for (item in viewModel.userIngredientLiveData.value!!) {
                    if (item.name.contains(searchString)) {
                        searchItemList.add(item)
                    }
                }
                homeIngredientAdapter.itemList = searchItemList
                if (binding.etSearchIngredient.text.isEmpty()) {
                    viewModel.getUserIngredient()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.getUserIngredient()
    }

    private fun toggleFab() {
        // 플로팅 액션 버튼 닫기 - 열려있는 플로팅 버튼 집어넣는 애니메이션
        if (isFabOpen) {
            ObjectAnimator.ofFloat(binding.btnAddIngredient, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.btnEditIngredient, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.btnEtc, View.ROTATION, 180f, 0f).apply { start() }
        } else { // 플로팅 액션 버튼 열기 - 닫혀있는 플로팅 버튼 꺼내는 애니메이션
            ObjectAnimator.ofFloat(binding.btnAddIngredient, "translationY", -360f).apply { start() }
            ObjectAnimator.ofFloat(binding.btnEditIngredient, "translationY", -180f).apply { start() }
            ObjectAnimator.ofFloat(binding.btnEtc, View.ROTATION, 0f, 180f).apply { start() }
        }
        isFabOpen = !isFabOpen
    }

    private fun selectMode(em:Boolean){
        if(em){ // 편집 모드
            val db = Firebase.firestore
            var dataOrigin:Ingredient = Ingredient()
            var editItName:String = "nullName"
            var editItDate:String = "nullDate"
            var editItJpg:String = "nullJpg"
            var editItAdded:String = "nullAdded"

            homeIngredientAdapter = HomeIngredientAdapter(ArrayList(), em, parentFragmentManager)
            binding.ingredientRecyclerView.adapter=homeIngredientAdapter
            viewModel.getUserIngredient()

            homeIngredientAdapter.setItemClickListener(object: HomeIngredientAdapter.OnItemClickListener{
                override fun onClick(v: View, position: Int, m:String) {
                    // 클릭 시 이벤트 작성
                    Log.d("clickk","${homeIngredientAdapter.itemList[position].name}"+"${homeIngredientAdapter.itemList[position].date}")
                    editItName = homeIngredientAdapter.itemList[position].name
                    editItDate = homeIngredientAdapter.itemList[position].date
                    editItAdded = homeIngredientAdapter.itemList[position].added
                    editItJpg = homeIngredientAdapter.itemList[position].imageClass
                    dataOrigin = homeIngredientAdapter.itemList[position]

                    if(m=="edit")
                        IngredientDateDialog().show(parentFragmentManager, "date")
                    else if (m=="remove"){
                        Log.d("dataOriginn",homeIngredientAdapter.itemList[position].toString())
                        db.collection("users").document(MyApplication.prefs.getString("email","null"))
                            .update("ingredients", FieldValue.arrayRemove(dataOrigin))
                        viewModel.getUserIngredient()
                    }
                }
            })
            //유통기한 받아오기
            setFragmentResultListener("requestDate") { _, bundle ->
                val result = bundle.getString("date")
                Log.d("resultt", result.toString())

                val data =  hashMapOf(
                    "name" to editItName,
                    "date" to result,
                    "added" to editItAdded,
                    "imageClass" to editItJpg
                )

                db.collection("users").document(MyApplication.prefs.getString("email","null"))
                    .update("ingredients", FieldValue.arrayUnion(data)).addOnCompleteListener{
                        db.collection("users").document(MyApplication.prefs.getString("email","null"))
                            .update("ingredients", FieldValue.arrayRemove(dataOrigin))
                        viewModel.getUserIngredient()
                    }
            }

            binding.btnEtc.visibility =INVISIBLE
            binding.btnAddIngredient.visibility = INVISIBLE
            binding.btnEditIngredient.visibility = INVISIBLE
            binding.btnCheck.visibility = VISIBLE
        }else{ // 일반 모드
            homeIngredientAdapter = HomeIngredientAdapter(ArrayList(), em, parentFragmentManager)
            binding.ingredientRecyclerView.adapter=homeIngredientAdapter
            viewModel.getUserIngredient()

            binding.btnEtc.visibility =VISIBLE
            binding.btnAddIngredient.visibility =VISIBLE
            binding.btnEditIngredient.visibility =VISIBLE
            binding.btnCheck.visibility = INVISIBLE
        }
    }


}


package com.example.naenaeng

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import com.example.naenaeng.databinding.ActivityMainBinding
import com.example.naenaeng.ui.home.HomeFragment
import com.example.naenaeng.ui.mypage.MyPageFragment
import com.example.naenaeng.ui.recipe.RecipeFragment

private const val TAG_HOME = "home_fragment"
private const val TAG_RECIPE = "recipe_fragment"
private const val TAG_MY_PAGE = "my_page_fragment"

class MainActivity: AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //맨 처음 보여줄 프래그먼트 설정
        setFragment(TAG_HOME, HomeFragment())

        //네비 항목 클릭 시 프래그먼트 변경하는 함수 호출
        binding.navBottom.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.homeFragment -> setFragment(TAG_HOME, HomeFragment())
                R.id.recipeFragment -> setFragment(TAG_RECIPE, RecipeFragment())
                R.id.mypageFragment-> setFragment(TAG_MY_PAGE, MyPageFragment())
            }
            true
        }

        /*
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

         */
    }

    //프래그먼트 컨트롤 함수
    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        //트랜잭션에 tag로 전달된 fragment가 없을 경우 add
        if (manager.findFragmentByTag(tag) == null){
            fragTransaction.add(R.id.navFrameLayout, fragment, tag)
        }

        val home = manager.findFragmentByTag(TAG_HOME)
        val recipe = manager.findFragmentByTag(TAG_RECIPE)
        val myPage = manager.findFragmentByTag(TAG_MY_PAGE)

        //모든 프래그먼트 hide
        if (home != null){
            fragTransaction.hide(home)
        }
        if (recipe != null){
            fragTransaction.hide(recipe)
        }
        if (myPage != null) {
            fragTransaction.hide(myPage)
        }
        //선택한 항목에 따라 그에 맞는 프래그먼트만 show
        if (tag == TAG_HOME) {
            if (home != null) {
                binding.toolbar.visibility = View.VISIBLE
                fragTransaction.show(home)
            }
        }
        else if (tag == TAG_RECIPE) {
            if (recipe != null) {
                binding.toolbar.visibility = View.VISIBLE
                fragTransaction.show(recipe)
            }
        }
        else if (tag == TAG_MY_PAGE){
            if (myPage != null){
                binding.toolbar.visibility = View.VISIBLE
                fragTransaction.show(myPage)
            }
        }

        fragTransaction.commitAllowingStateLoss()
    }
}
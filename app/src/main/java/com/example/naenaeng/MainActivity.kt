package com.example.naenaeng

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.naenaeng.databinding.ActivityMainBinding
import com.example.naenaeng.ui.home.HomeFragment
import com.example.naenaeng.ui.mypage.MyPageFragment
import com.example.naenaeng.ui.recipe.RecipeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val TAG_HOME = "home_fragment"
private const val TAG_RECIPE = "recipe_fragment"
private const val TAG_MY_PAGE = "my_page_fragment"

class MainActivity: AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBottomNav()

    }
    private fun setBottomNav() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottom_nav)
            .setupWithNavController(navController)
    }

    fun setToolbarTitle(tag: String){
        when (tag) {
            TAG_HOME -> {
                binding.toolbar.visibility = View.INVISIBLE
            }
            TAG_RECIPE -> {
                binding.toolbar.visibility = View.VISIBLE
                binding.tvToolbarName.text = "레시피"
            }
            TAG_MY_PAGE -> {
                binding.toolbar.visibility = View.VISIBLE
                binding.tvToolbarName.text = "마이"
            }
            else -> {
                binding.toolbar.visibility=View.VISIBLE
                binding.tvToolbarName.text = tag
            }
        }
    }
}
package com.example.naenaeng

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.naenaeng.databinding.ActivityMainBinding
import com.example.naenaeng.ui.home.HomeFragment
import com.example.naenaeng.ui.mypage.MyPageFragment
import com.example.naenaeng.ui.recipe.RecipeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

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

    fun setToolbar(tag: String){
        when (tag) {
            "none" -> {
                binding.toolbar.visibility = View.GONE
                binding.bottomNav.visibility = View.VISIBLE
            }
            "none2" -> {
                binding.toolbar.visibility = View.GONE
                binding.bottomNav.visibility = View.GONE
            }
            else -> {
                binding.toolbar.visibility=View.VISIBLE
                binding.bottomNav.visibility = View.VISIBLE
                binding.tvToolbarName.text = tag
            }
        }
    }
}
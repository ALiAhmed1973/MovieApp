package com.aliprojects.movieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.aliprojects.movieapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController= navHost.navController
//        val destinations = setOf(R.id.moviesFragment, R.id.favoriteMoviesFragment)
//
//        val appBarConfiguration = AppBarConfiguration(destinations)

        binding.bottomNavigation.setupWithNavController(navController)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
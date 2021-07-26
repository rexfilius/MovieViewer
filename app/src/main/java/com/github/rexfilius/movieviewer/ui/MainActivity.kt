package com.github.rexfilius.movieviewer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.github.rexfilius.movieviewer.R
import com.github.rexfilius.movieviewer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get NavHostFragment and NavController
        val navHostFrag =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFrag.navController

        // Define AppBarConfiguration
        val topLevelDestinations = setOf(R.id.movieListFragment, R.id.movieFavoriteFragment)
        val appBarConfiguration = AppBarConfiguration(topLevelDestinations)

        // Connect Toolbar with NavController
        binding.mainToolbar.setupWithNavController(navController, appBarConfiguration)

        // Connect BottomNavigationView with NavController
        binding.bottomNavView.setupWithNavController(navController)
    }
}
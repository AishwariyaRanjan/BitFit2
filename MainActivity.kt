package com.example.bitfitpart1

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfitpart1.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fragmentManager: FragmentManager = supportFragmentManager

        // define fragments here:
        val nutritionListFragment: Fragment = NutritionListFragment()
        val dashboardFragment: Fragment = DashboardFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.log -> fragment = nutritionListFragment
                R.id.dashBoard -> fragment = dashboardFragment
            }
            // casting?
            replaceFragment(fragment)
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.log
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nutritionList_frame_layout, fragment)
        fragmentTransaction.commit()
    }
}
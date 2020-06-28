package com.smarttoolfactory.tutorial7_1bnw_viewpager2_nestednavigation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.smarttoolfactory.tutorial7_1bnw_viewpager2_nestednavigation.adapter.ActivityFragmentStateAdapter
import com.smarttoolfactory.tutorial7_1bnw_viewpager2_nestednavigation.databinding.ActivityMainBinding
import com.smarttoolfactory.tutorial7_1bnw_viewpager2_nestednavigation.viewmodel.AppbarViewModel

class MainActivity : AppCompatActivity() {

//    private val appbarViewModel by viewModels<AppbarViewModel>()<AppbarViewModel>()

    private val appbarViewModel:AppbarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewPager2 = dataBinding.viewPager
        val bottomNavigationView = dataBinding.bottomNav

        // Cancel ViewPager swipe
        viewPager2.isUserInputEnabled = false

        // Set viewpager adapter
        viewPager2.adapter = ActivityFragmentStateAdapter(this)

        // Listen bottom navigation tabs change
        bottomNavigationView.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.nav_graph_home -> {
                    viewPager2.setCurrentItem(0, false)
                    return@setOnNavigationItemSelectedListener true

                }
                R.id.nav_graph_dashboard -> {
                    viewPager2.setCurrentItem(1, false)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_graph_notification -> {
                    viewPager2.setCurrentItem(2, false)
                    return@setOnNavigationItemSelectedListener true
                }
            }

            false

        }


        appbarViewModel.currentNavController.observe(this, Observer { navController ->
            navController?.let {
                val appBarConfig = AppBarConfiguration(it.graph)
                dataBinding.toolbar.setupWithNavController(it, appBarConfig)
            }
        })

    }
}
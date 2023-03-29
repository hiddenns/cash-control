package com.cashcontrol.feature.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cashcontrol.R
import com.cashcontrol.databinding.ActivityMainBinding
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import org.kodein.di.DI.Companion.lazy


class MainActivity : AppCompatActivity(), DIAware {
    protected lateinit var binding: ActivityMainBinding

    override val di: DI by closestDI()
    private val mainView by lazy { window.decorView }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController

        val navBottomView = binding.navigationView
        navBottomView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.outlayItem -> {
                    navController.navigate(R.id.global_to_incomeFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.incomeItem -> {
                    navController.navigate(R.id.global_to_outlayFragment)
                    return@setOnItemSelectedListener true
                }

            }
            return@setOnItemSelectedListener true
        }


    }



}
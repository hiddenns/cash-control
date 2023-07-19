package com.cashcontrol.feature.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.cashcontrol.databinding.ActivityMainBinding
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI


class MainActivity : AppCompatActivity(), DIAware {
    private var binding: ActivityMainBinding? = null

    override val di: DI by closestDI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        with(binding!!.viewPager) {
            adapter = PagerAdapter(this@MainActivity)
            setCurrentItem(1, false)
        }

    }


}
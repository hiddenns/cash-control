package com.cashcontrol.feature.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cashcontrol.databinding.ActivityMainBinding
import com.cashcontrol.feature.view.PagerAdapter
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI


class MainActivity : AppCompatActivity(), DIAware {
    protected lateinit var binding: ActivityMainBinding

    override val di: DI by closestDI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.viewPager) {
            adapter = PagerAdapter(this@MainActivity)
            setCurrentItem(1, false)
            adapter = adapter
            setCurrentItem(1, false)
        }

    }


}
package com.cashcontrol.feature.view

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cashcontrol.feature.view.activity.MainActivity
import com.cashcontrol.feature.view.activity.MainFragment
import com.cashcontrol.feature.view.settings.SettingsFragment
import com.cashcontrol.feature.view.statistics.StatisticsFragment

class PagerAdapter(mainActivity: MainActivity) : FragmentStateAdapter(mainActivity) {

    override fun getItemCount() = countFragments

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            STATISTICS_FRAGMENT_POSITION -> StatisticsFragment()
            MAIN_FRAGMENT_POSITION -> MainFragment()
            SETTINGS_FRAGMENT_POSITION -> SettingsFragment()
            else -> throw UnsupportedOperationException()
        }
    }

    companion object {
        private const val countFragments = 3
        private const val STATISTICS_FRAGMENT_POSITION = 0
        private const val MAIN_FRAGMENT_POSITION = 1
        private const val SETTINGS_FRAGMENT_POSITION = 2
    }
}
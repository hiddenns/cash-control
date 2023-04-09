package com.cashcontrol.feature.view.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import androidx.recyclerview.widget.SnapHelper
import com.cashcontrol.databinding.FragmentMainBinding
import com.cashcontrol.domain.base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    var isStateDelayScrolled = false
    var isStateDelayManyTouchDown = false

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvTransactionList.layoutManager = LinearLayoutManager(context)
        binding.rvTransactionList.adapter = TransactionListAdapter(emptyList())

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvTransactionList)

        binding.svParent.isVerticalScrollBarEnabled = false
        binding.svParent.setOnTouchListener { _, _ -> true }

        binding.rvTransactionList.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!isStateDelayScrolled) {
                    isStateDelayScrolled = true
                    val currentItemPosition = (recyclerView.layoutManager as LinearLayoutManager)
                        .findFirstVisibleItemPosition()

                    //scroll up
                    if (dy > COORDINATE_ONE && currentItemPosition > SECOND_POSITION && !isStateDelayManyTouchDown) {
                        isStateDelayManyTouchDown = true
                        binding.svParent.smoothScrollTo(FIRST_POSITION, binding.textView2.top)
                        lifecycleScope.launch {
                            delay(MANY_TOUCH_DELAY)
                            isStateDelayManyTouchDown = false
                        }
                        //scroll down
                    } else if (dy < COORDINATE_ZERO && currentItemPosition in FIRST_POSITION..SECOND_POSITION) {
                        binding.svParent.smoothScrollTo(FIRST_POSITION, binding.svParent.top)
                    }

                    lifecycleScope.launch {
                        delay(MANY_TOUCH_DELAY)
                        isStateDelayScrolled = false
                    }

                }
            }
        })
        binding.textView2.setOnClickListener {
            binding.svParent.smoothScrollTo(FIRST_POSITION, binding.svParent.top)
            isStateDelayScrolled = true
            lifecycleScope.launch {
                delay(MANY_TOUCH_DELAY)
                isStateDelayScrolled = false
            }
        }
    }


    companion object {
        private const val FIRST_POSITION = 0
        private const val SECOND_POSITION = 1

        private const val COORDINATE_ZERO = 0
        private const val COORDINATE_ONE = 1

        private const val MANY_TOUCH_DELAY = 150L
    }
}
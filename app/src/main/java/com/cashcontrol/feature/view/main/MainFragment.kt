package com.cashcontrol.feature.view.main

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import androidx.recyclerview.widget.SnapHelper
import com.cashcontrol.data.model.Category
import com.cashcontrol.data.model.Transaction
import com.cashcontrol.databinding.FragmentMainBinding
import com.cashcontrol.domain.base.BaseFragment
import com.cashcontrol.domain.extenstion.observe
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.kodein.di.instance
import java.util.*

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private var isStateDelayScrolled = false
    private var isStateDelayManyTouchDown = false


    private val viewModel: MainViewModel by instance()

    private val transactionListAdapter = TransactionListAdapter(mutableListOf())

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            svParent.isVerticalScrollBarEnabled = false
            svParent.setOnTouchListener { _, _ -> true }
            val snapHelperCards: SnapHelper = LinearSnapHelper()
            val snapHelperTransactions: SnapHelper = LinearSnapHelper()

            rvCards.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rvCards.adapter = ActionTypeAdapter()
            snapHelperCards.attachToRecyclerView(rvCards)

            rvTransactionList.layoutManager = LinearLayoutManager(context)
            rvTransactionList.adapter = transactionListAdapter
            snapHelperTransactions.attachToRecyclerView(rvTransactionList)
        }

        setListeners()
        setupObservers()
    }

    private fun setupObservers() {
        with(viewModel) {
            categories.observe(lifecycleScope) { list ->
                transactionListAdapter.updateData(
                    list.flatMap {
                        it.transactions
                    })
            }

        }
    }

    private fun setListeners() {
        binding.rvTransactionList.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!isStateDelayScrolled) {
                    isStateDelayScrolled = true
                    val currentItemPosition = (recyclerView.layoutManager as LinearLayoutManager)
                        .findFirstVisibleItemPosition()

                    //scroll up
                    if (dy > COORDINATE_ONE && currentItemPosition > SECOND_POSITION && !isStateDelayManyTouchDown) {
                        isStateDelayManyTouchDown = true
                        animate(binding.svParent, binding.expansesText.top)
                        lifecycleScope.launch {
                            delay(MANY_TOUCH_DELAY)
                            isStateDelayManyTouchDown = false
                        }
                        //scroll down
                    } else if (dy < COORDINATE_ZERO && currentItemPosition in FIRST_POSITION..SECOND_POSITION) {
                        animate(binding.svParent, binding.svParent.top)
                    }

                    lifecycleScope.launch {
                        delay(MANY_TOUCH_DELAY)
                        isStateDelayScrolled = false
                    }

                }
            }
        })

        binding.expansesText.setOnClickListener {
            isStateDelayScrolled = true
            animate(binding.svParent, binding.svParent.top)
            binding.rvTransactionList.smoothScrollToPosition(FIRST_POSITION)
            lifecycleScope.launch {
                delay(MANY_TOUCH_DELAY)
                isStateDelayScrolled = false
            }
        }
    }

    private fun animate(view: View, positionY: Int, propertyName: String = "scrollY") {
        ObjectAnimator.ofInt(view, propertyName, positionY).setDuration(ANIMATION_DELAY).start()
    }


    companion object {
        private const val FIRST_POSITION = 0
        private const val SECOND_POSITION = 1

        private const val COORDINATE_ZERO = 0
        private const val COORDINATE_ONE = 1

        private const val MANY_TOUCH_DELAY = 150L
        private const val ANIMATION_DELAY = 190L
    }
}
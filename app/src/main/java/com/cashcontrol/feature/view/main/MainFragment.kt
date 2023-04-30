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
import com.cashcontrol.R
import com.cashcontrol.data.model.Transaction
import com.cashcontrol.databinding.FragmentMainBinding
import com.cashcontrol.domain.base.BaseFragment
import com.cashcontrol.feature.main.dialogs.ActionBottomSheetDialog
import com.cashcontrol.feature.main.dialogs.ActionDialogSubmitCallBack
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.kodein.di.instance

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate),
    ActionDialogSubmitCallBack {

    private var isStateDelayScrolled = false
    private var isStateDelayManyTouchDown = false


    private val viewModel: MainViewModel by instance()

    private val transactionListAdapter = TransactionListAdapter(mutableListOf())
    private val actionTypeAdapter = ActionTypeAdapter(mutableListOf()) { position ->
        callActionDialog(position)
    }

    private lateinit var actionBottomSheetDialog: ActionBottomSheetDialog


    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            svParent.isVerticalScrollBarEnabled = false
            svParent.setOnTouchListener { _, _ -> true }
            val snapHelperCards: SnapHelper = LinearSnapHelper()
            val snapHelperTransactions: SnapHelper = LinearSnapHelper()

            rvCards.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvCards.adapter = actionTypeAdapter
            snapHelperCards.attachToRecyclerView(rvCards)

            rvTransactionList.layoutManager = LinearLayoutManager(context)
            rvTransactionList.adapter = transactionListAdapter
            snapHelperTransactions.attachToRecyclerView(rvTransactionList)
        }
        actionBottomSheetDialog = ActionBottomSheetDialog(requireContext())
        actionBottomSheetDialog.setContentView(R.layout.action_dialog)
        actionBottomSheetDialog.submitCallBack = this
        setListeners()
        setupObservers()
    }

    private fun setupObservers() {
        with(viewModel) {
            transactions.observe { data ->
                transactionListAdapter.updateData(data)
            }

            actions.observe { actions ->
                actionTypeAdapter.updateData(actions)
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

        binding.rvCards
    }

    private fun callActionDialog(position: Int) {
        when (position) {
            EXPANSES_ACTION_POSITION -> {
                actionBottomSheetDialog.tvTitle?.text =
                    context?.getText(R.string.expanses)
            }
            INCOME_ACTION_POSITION -> {
                actionBottomSheetDialog.tvTitle?.text =
                    context?.getText(R.string.income)
            }
            else -> {
                throw IllegalArgumentException("cannot find action type with position: $position")
            }
        }
        actionBottomSheetDialog.show()

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

        private const val EXPANSES_ACTION_POSITION = 0
        private const val INCOME_ACTION_POSITION = 1
    }

    override fun onClose(transaction: Transaction) {
        viewModel.addTransaction(transaction)
    }

}
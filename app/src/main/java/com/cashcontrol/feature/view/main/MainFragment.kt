package com.cashcontrol.feature.view.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.cashcontrol.R
import com.cashcontrol.data.model.Transaction
import com.cashcontrol.databinding.FragmentMainBinding
import com.cashcontrol.domain.base.BaseFragment
import com.cashcontrol.feature.main.dialogs.ActionBottomSheetDialog
import com.cashcontrol.feature.main.dialogs.ActionDialogSubmitCallBack
import org.kodein.di.instance

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate),
    ActionDialogSubmitCallBack {

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
            binding.rv.adapter = transactionListAdapter
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

        }
    }

    private fun setListeners() {

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

    override fun onClose(transaction: Transaction) {
        viewModel.addTransaction(transaction)
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

}
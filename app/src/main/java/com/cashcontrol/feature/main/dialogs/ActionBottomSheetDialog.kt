package com.cashcontrol.feature.main.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.cashcontrol.R
import com.cashcontrol.data.model.Category
import com.cashcontrol.data.model.Transaction
import com.cashcontrol.databinding.ActionDialogBinding
import com.cashcontrol.feature.util.getCurrentTimeUtc
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI
import org.kodein.di.instance
import kotlin.random.Random

class ActionBottomSheetDialog : BottomSheetDialogFragment(R.layout.action_dialog), DIAware {

    private var _binding: ActionDialogBinding? = null
    private val binding: ActionDialogBinding
        get() = _binding!!

    override val di: DI by closestDI()

    override fun getTheme() = R.style.CustomBottomSheetDialog

    private val categoryItemsAdapter = CategoryItemsAdapter(emptyList())

    private val viewModel: ActionDialogViewModel by instance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActionDialogBinding.inflate(layoutInflater)
        return _binding!!.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialog)
        _binding = ActionDialogBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupObservers()
        setupViews()
    }

    private fun setupViews() {
        with(binding) {
            rvCategories.adapter = categoryItemsAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObservers() {
        with(viewModel) {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.CREATED) {
                    categories.collectLatest { list ->
                        categoryItemsAdapter.submit(list)
                    }
                }
            }
        }
    }

    private fun setupListeners() {
        with(binding) {
            btnSubmit.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    val newTransaction = Transaction(
                        category = viewModel.categories.firstOrNull()?.get(0) ?: Category(),
                        sum = Integer.parseInt(binding.etSum.text.toString()),
                        date = getCurrentTimeUtc(),
                        description = etDescription.text.toString(),
                        transactionId = Random(43).nextInt()
                    )

                    viewModel.addTransaction(newTransaction)
                }
                dismiss()
            }
        }
    }

    companion object {

        const val TAG = "ACTION_DIALOG"
        fun show(fragmentManager: FragmentManager): ActionBottomSheetDialog {
            val dialog = ActionBottomSheetDialog()
            dialog.show(fragmentManager, TAG)
            return dialog
        }
    }

}
package com.cashcontrol.feature.main.dialogs

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.GridLayoutManager
import com.cashcontrol.R
import com.cashcontrol.data.model.ActionType
import com.cashcontrol.data.model.Category
import com.cashcontrol.data.model.Transaction
import com.cashcontrol.databinding.ActionDialogBinding
import com.cashcontrol.feature.util.CategorySelectionTracker
import com.cashcontrol.feature.util.ItemDetailsLookup
import com.cashcontrol.feature.util.KeyProvider
import com.cashcontrol.feature.util.getCurrentDateTime
import com.cashcontrol.feature.util.getCurrentTimeUtc
import com.cashcontrol.feature.util.getFullDateFormat
import com.cashcontrol.feature.util.toString
import com.cashcontrol.feature.view.ext.launchWhenCreated
import com.cashcontrol.feature.view.ext.showKeyboard
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI
import org.kodein.di.instance
import java.util.Currency
import java.util.Locale

class ActionBottomSheetDialog : BottomSheetDialogFragment(R.layout.action_dialog), DIAware {

    private var _binding: ActionDialogBinding? = null
    private val binding: ActionDialogBinding
        get() = _binding!!

    override val di: DI by closestDI()

    override fun getTheme() = R.style.CustomBottomSheetDialog

    private val categoryItemsAdapter = CategoryItemsAdapter()

    private val viewModel: ActionDialogViewModel by instance()

    private var tracker: SelectionTracker<Long>? = null

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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupListeners()
        setupObservers()
    }

    override fun onResume() {
        super.onResume()
        binding.etSum.showKeyboard()
    }

    private fun setupViews() {
        setupRecycler()
        setSumEditText()
        with(binding) {
            btnCalendarPicker.text = getCurrentDateTime().toString(getFullDateFormat())
            tvCurrency.text = Currency.getInstance(Locale.getDefault()).symbol

            rgFilterPicker.setOnCheckedChangeListener { radioGroup, checkId ->
                when (checkId) {
                    R.id.rb_expanse ->
                        viewModel.setActionType(ActionType.EXPENSE)

                    R.id.rb_income ->
                        viewModel.setActionType(ActionType.INCOME)
                }
            }
        }
    }

    private fun setSumEditText() {
        with(binding) {
            etSum.isCursorVisible = false
            etSum.isLongClickable = false
            etSum.setBackgroundResource(android.R.color.transparent)
            etSum.requestFocus()
            etSum.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(p0: Editable?) {
                    var current = p0.toString()
                    if (current.isBlank()) {
                        current = "0"
                    }
                    tvSum.text = current
                }

            })
        }
    }

    private fun setupRecycler() {
        with(binding) {
            val flexLayoutManager =
                GridLayoutManager(requireContext(), 3, GridLayoutManager.HORIZONTAL, false)
            rvCategories.adapter = categoryItemsAdapter
            rvCategories.layoutManager = flexLayoutManager
            tracker = SelectionTracker.Builder(
                "mySelection",
                rvCategories,
                KeyProvider(rvCategories),
                ItemDetailsLookup(rvCategories),
                StorageStrategy.createLongStorage()
            ).withSelectionPredicate(
                CategorySelectionTracker<Long>()
            ).build()
            categoryItemsAdapter.tracker = tracker
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObservers() {
        with(viewModel) {
            launchWhenCreated {
                categories.collectLatest { list ->
                    categoryItemsAdapter.submitList(list)
                }
            }

        }
        tracker?.addObserver(
            object : SelectionTracker.SelectionObserver<Long>() {
                override fun onSelectionChanged() {
                    super.onSelectionChanged()
                    //Toast.makeText(requireContext(), "select ${tracker?.selection?.size()}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun setupListeners() {
        with(binding) {
            btnSubmit.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    val newTransaction = Transaction(
                        category = viewModel.categories.firstOrNull()
                            ?.find { item ->
                                item.categoryId == categoryItemsAdapter.currentList[(tracker?.selection?.first())?.toInt()
                                    ?: -1].categoryId
                            }
                            ?: Category(),
                        sum = Integer.parseInt(etSum.text.toString()),
                        date = getCurrentTimeUtc(),
                        description = etDescription.text.toString()
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
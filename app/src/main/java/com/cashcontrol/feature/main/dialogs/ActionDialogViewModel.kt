package com.cashcontrol.feature.main.dialogs

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.cashcontrol.data.model.ActionType
import com.cashcontrol.data.model.Category
import com.cashcontrol.data.model.Transaction
import com.cashcontrol.domain.base.BaseViewModel
import com.cashcontrol.domain.usecases.GetCategoriesInteract
import com.cashcontrol.domain.usecases.GetTransactionsInteract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActionDialogViewModel(
    private val categoriesInteract: GetCategoriesInteract,
    private val transactionInteract: GetTransactionsInteract
) : BaseViewModel() {

    private val _actionType = MutableStateFlow(ActionType.EXPENSE)

    private val _categories: Flow<List<Category>> = categoriesInteract.getAllCategories()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
        .combine(_actionType) { list, type ->
            list.filter { item -> item.type == type }
        }
    var categories = _categories

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            Log.d("12345", "addTransaction: ")
            withContext(Dispatchers.IO) {
                transactionInteract.addTransaction(transaction)
            }
        }
    }

    fun setActionType(actionType: ActionType) {
        viewModelScope.launch {
            _actionType.emit(actionType)
        }
    }

}
package com.cashcontrol.feature.view.main

import androidx.lifecycle.viewModelScope
import com.cashcontrol.data.model.Category
import com.cashcontrol.data.model.Transaction
import com.cashcontrol.domain.base.BaseViewModel
import com.cashcontrol.domain.usecases.GetCategoriesInteract
import com.cashcontrol.domain.usecases.GetTransactionsInteract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val categoriesInteract: GetCategoriesInteract,
    private val transactionInteract: GetTransactionsInteract
) : BaseViewModel() {

    private val _transactions = transactionInteract.getAllTransactions()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
    var transactions = _transactions

    init {

    }

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch(Dispatchers.IO) {
            transactionInteract.addTransaction(transaction)
        }
    }

    fun addCategory(category: Category) {
        viewModelScope.launch {
            categoriesInteract.addCategory(category)
        }
    }

    companion object {

    }
}
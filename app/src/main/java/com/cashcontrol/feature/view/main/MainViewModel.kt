package com.cashcontrol.feature.view.main

import androidx.lifecycle.viewModelScope
import com.cashcontrol.data.model.Category
import com.cashcontrol.data.model.Transaction
import com.cashcontrol.domain.base.BaseViewModel
import com.cashcontrol.domain.usecases.GetCategoriesInteract
import com.cashcontrol.domain.usecases.GetTransactionsInteract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val categoriesInteract: GetCategoriesInteract,
    private val transactionInteract: GetTransactionsInteract
) : BaseViewModel() {

    private val _event = MutableStateFlow<Event?>(null)
    var event = _event.asStateFlow()

    private val _transactions = transactionInteract.getAllTransactions()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
        .combine(_event) { transactions, event ->
            when (event) {
                Event.SelectTransactionsFilter -> transactions
                Event.SelectCategoriesFilter -> {
                    val categories = transactionInteract.getAllTransactions().firstOrNull()?.map { transaction ->
                        transaction.category
                    } ?: emptyList()
                    val setCategory = categories.toSet()
                    setCategory.map { category ->
                        var categorySum = 0
                        val cats = transactions.forEach { transaction ->
                            if (transaction.category == category) {
                                categorySum += transaction.sum
                            }
                        }.let {
                            category.sum = categorySum.toLong()
                            category
                        }
                        cats
                    }
                    setCategory.map { category ->
                        Transaction(category = category).apply {
                            description = category.name
                            sum = category.sum.toInt()
                        }
                    }
                }

                else -> {
                    transactions
                }
            }
        }

    private val _categories = _transactions.map { transactions ->
        val categories = transactions.map { transaction ->
            transaction.category
        }

        val setCategory = HashSet(categories).map { category ->
            var categorySum = 0
            val cats = transactions.forEach { transaction ->
                if (transaction.category == category) {
                    categorySum += transaction.sum
                }
            }.let {
                category.sum = categorySum.toLong()
                category
            }
            cats
        }
        setCategory
    }

    var categories = _categories


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

    fun setEvent(event: Event) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    sealed class Event {
        object SelectTransactionsFilter : Event()
        object SelectCategoriesFilter : Event()

    }

    companion object {

    }
}
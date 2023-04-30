package com.cashcontrol.feature.view.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.cashcontrol.R
import com.cashcontrol.data.model.Action
import com.cashcontrol.data.model.ActionTypes
import com.cashcontrol.data.model.Category
import com.cashcontrol.data.model.Transaction
import com.cashcontrol.data.room.entity.PaymentMethod
import com.cashcontrol.domain.base.BaseViewModel
import com.cashcontrol.domain.usecases.GetCategoriesInteract
import com.cashcontrol.domain.usecases.GetTransactionsInteractor
import com.cashcontrol.domain.usecases.PaymentMethodInteract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class MainViewModel(
    private val categoriesInteract: GetCategoriesInteract,
    private val transactionInteract: GetTransactionsInteractor,
    private val paymentMethodInteract: PaymentMethodInteract
) : BaseViewModel() {

//    private var _categories = flow {
//        emit(categoriesInteract.getAllCategories())
//    }.flowOn(Dispatchers.IO)
//
//    var categories = _categorie

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    var categories = _categories.asStateFlow()

    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    var transactions = _transactions.asStateFlow()

//    private val _actions = flow {
//        emit(actionsList)
//    }.flowOn(Dispatchers.IO)
//    var actions = _actions


    private val _actions =
        paymentMethodInteract.getAllPaymentMethods()
            .flowOn(Dispatchers.IO)
    var actions = _actions


//    fun getAllCategories() {
//        viewModelScope.launch {
//            categoriesInteract.getAllCategories().let { categories ->
//                _categories.emit(categories)
//            }
//        }
//    }

    init {
        viewModelScope.launch {
            _categories.value = categoriesInteract.getAllCategories()
        }
        Log.e("dbase", "init" )
        GlobalScope.launch(Dispatchers.IO) {
            Log.e("dbase", "start:" )
            paymentMethodInteract.insertPaymentMethods(
                PaymentMethod(
                    "id",
                    "name1",
                    23L,
                    listOf(com.cashcontrol.data.room.entity.Transaction()),
                    "-1"
                )
            )
            Log.e("dbase", "inserted" )
        }
    }


    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            _transactions.value = categoriesInteract.getAllCategories().flatMap { category ->
                category.transactions
            }

            GlobalScope.launch(Dispatchers.IO) {
                val xx = paymentMethodInteract.getAllPaymentMethods()
                Log.e("dbase", xx.toString())
            }
//            Log.e("db", latestNews. )
            println()
        }
    }

    companion object {
        //        val actionsList = listOf(
        val actionsList = listOf(
            Action(
                ActionTypes.EXPANSES,
                R.string.expanses,
                300,
                2,
                R.color.purple_200,
                org.kodein.di.android.support.R.drawable.abc_ab_share_pack_mtrl_alpha
            ),
            Action(
                ActionTypes.INCOME,
                R.string.income,
                1200,
                5,
                R.color.teal_200,
                R.drawable.ic_launcher_foreground
            )
        )
    }
}
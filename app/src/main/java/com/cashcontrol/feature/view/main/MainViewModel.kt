package com.cashcontrol.feature.view.main

import androidx.lifecycle.viewModelScope
import com.cashcontrol.data.model.Category
import com.cashcontrol.domain.base.BaseViewModel
import com.cashcontrol.domain.usecases.GetCategoriesInteract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    val categoriesInteractor: GetCategoriesInteract
): BaseViewModel() {

    private val _categories = flow {
        categoriesInteractor.getAllCategories().let { categories ->
            emit(categories)
        }
    }.flowOn(Dispatchers.IO)

    var categories = _categories

//    fun getAllCategories() {
//        viewModelScope.launch {
//            categoriesInteractor.getAllCategories().let { categories ->
//                _categories.emit(categories)
//            }
//        }
//    }


}
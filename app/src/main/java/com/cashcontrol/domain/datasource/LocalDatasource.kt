package com.cashcontrol.domain.datasource

import com.cashcontrol.core.database.AppDatabase
import com.cashcontrol.data.room.entity.PaymentMethod
import com.cashcontrol.domain.base.BaseDataSource
import com.cashcontrol.domain.repositories.dao.PaymentMethodsDao
import kotlinx.coroutines.flow.Flow

class LocalDatasource(
   private val database: AppDatabase
): BaseDataSource() {

    init {
        database.openHelper.writableDatabase
    }

    private var paymentMethodsDao: PaymentMethodsDao = database.paymentMethodDao()

    fun getAllPaymentMethods() = paymentMethodsDao.getAll()

    fun insertPaymentMethods(vararg paymentMethod: PaymentMethod) = paymentMethodsDao.insertAll(*paymentMethod)



}
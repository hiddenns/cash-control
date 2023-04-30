package com.cashcontrol.domain.repositories.paymentMethods

import com.cashcontrol.data.room.entity.PaymentMethod
import com.cashcontrol.domain.datasource.LocalDatasource
import kotlinx.coroutines.flow.Flow

class PaymentMethodsRepositoryImpl(private val localDatasource: LocalDatasource) {

    fun getAllPaymentMethods() =
        localDatasource.getAllPaymentMethods()

    fun insertPaymentMethods(paymentMethod: PaymentMethod) = localDatasource.insertPaymentMethods()

}
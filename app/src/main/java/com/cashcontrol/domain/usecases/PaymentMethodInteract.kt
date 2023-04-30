package com.cashcontrol.domain.usecases

import com.cashcontrol.data.room.entity.PaymentMethod
import com.cashcontrol.domain.repositories.paymentMethods.PaymentMethodsRepositoryImpl

class PaymentMethodInteract(private val paymentMethodsRepositoryImpl: PaymentMethodsRepositoryImpl) {

    fun getAllPaymentMethods() = paymentMethodsRepositoryImpl.getAllPaymentMethods()

    fun insertPaymentMethods(paymentMethod: PaymentMethod) = paymentMethodsRepositoryImpl.insertPaymentMethods(paymentMethod)
}
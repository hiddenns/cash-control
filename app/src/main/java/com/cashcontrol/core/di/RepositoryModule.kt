package com.cashcontrol.core.di

import com.cashcontrol.domain.repositories.categories.CategoriesRepositoryImpl
import com.cashcontrol.domain.repositories.paymentMethods.PaymentMethodsRepositoryImpl
import com.cashcontrol.domain.repositories.transactions.TransactionsRepository
import com.cashcontrol.domain.repositories.transactions.TransactionsRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val RepositoryModule = DI.Module("Repository") {
    bindProvider { CategoriesRepositoryImpl() }
    bindProvider { TransactionsRepositoryImpl() }
    bindProvider { PaymentMethodsRepositoryImpl(instance()) }
}
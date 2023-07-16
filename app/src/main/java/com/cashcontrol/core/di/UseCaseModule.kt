package com.cashcontrol.core.di

import com.cashcontrol.domain.usecases.GetCategoriesInteract
import com.cashcontrol.domain.usecases.GetTransactionsInteract
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val UseCaseModule = DI.Module("UseCase") {
    bindProvider { GetCategoriesInteract(instance()) }
    bindProvider { GetTransactionsInteract(instance()) }
}

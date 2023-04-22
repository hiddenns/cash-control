package com.cashcontrol.core.di

import com.cashcontrol.domain.base.BaseViewModel
import com.cashcontrol.domain.usecases.GetCategoriesInteract
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val UseCaseModule = DI.Module("UseCase") {
    bindProvider { GetCategoriesInteract(instance()) }
}

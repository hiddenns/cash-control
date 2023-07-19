package com.cashcontrol.core.di

import com.cashcontrol.domain.base.BaseViewModel
import com.cashcontrol.feature.main.dialogs.ActionDialogViewModel
import com.cashcontrol.feature.view.main.MainViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.bindProvider
import org.kodein.di.instance
import org.kodein.di.provider

val ViewModelModule = DI.Module("ViewModel") {
    bindProvider { BaseViewModel() }
    bind<MainViewModel>() with provider { MainViewModel(instance(), instance()) }
    bind<ActionDialogViewModel>() with provider { ActionDialogViewModel(instance(), instance()) }
}
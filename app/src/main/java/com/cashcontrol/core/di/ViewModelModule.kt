package com.cashcontrol.core.di

import com.cashcontrol.domain.base.BaseViewModel
import com.cashcontrol.feature.view.main.MainViewModel
import org.kodein.di.*

val ViewModelModule = DI.Module("ViewModel") {
    bindProvider { BaseViewModel() }
    bind<MainViewModel>() with provider { MainViewModel(instance(), instance()) }
}
package com.cashcontrol.core.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.cashcontrol.domain.base.BaseFragment
import com.cashcontrol.domain.base.BaseViewModel
import com.cashcontrol.feature.view.main.MainViewModel
import org.kodein.di.*
import org.kodein.di.android.x.androidXModule

val ViewModelModule = DI.Module("ViewModel") {
    bindProvider { BaseViewModel() }
    bind<MainViewModel>() with provider { MainViewModel(instance()) }
}

object AppModule {

    fun get(application: Application) = DI.Module("App") {
        import(androidXModule(application))
        //import(DomainModule)
        //import(DataModule)
        //import(LocalModule)
        import(ViewModelModule)
//        bind<> with provider { BaseViewModel() }
        import(UseCaseModule)
        import(RepositoryModule)
        //import(DataSourceModule)

//        bindSingleton { DispatcherProviderImpl() }
//        bindSingleton<SettingsManager> { SettingsManagerImpl(instance()) }
//        bindSingleton<AdvertisingManager> { AdvertisingManagerImpl(instance()) }
    }
}
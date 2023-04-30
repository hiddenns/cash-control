package com.cashcontrol.core.di

import android.app.Application
import org.kodein.di.DI
import org.kodein.di.android.x.androidXModule

object AppModule {

    fun get(application: Application) = DI.Module("App") {
        import(androidXModule(application))
        //import(DomainModule)
        //import(DataModule)
        import(localDatabase(application))
        import(ViewModelModule)
//        bind<> with provider { BaseViewModel() }
        import(UseCaseModule)
        import(RepositoryModule)
//        import(DataSourceModule)

//        bindSingleton { DispatcherProviderImpl() }
//        bindSingleton<SettingsManager> { SettingsManagerImpl(instance()) }
//        bindSingleton<AdvertisingManager> { AdvertisingManagerImpl(instance()) }
    }
}
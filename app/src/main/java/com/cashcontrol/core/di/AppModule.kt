package com.cashcontrol.core.di

import android.app.Application
import com.cashcontrol.domain.base.BaseViewModel
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bindProvider

val ViewModelModule = DI.Module("ViewModel") {
    bindProvider { BaseViewModel() }
//    bindProvider { AchievementsViewModel(instance()) }
}

class AppModule : Application(), DIAware {
    override val di = DI.lazy {
        import(ViewModelModule)
    }


}
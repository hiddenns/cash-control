package com.cashcontrol.core.di

import android.app.Application
import org.kodein.di.*

//import timber.log.Timber

class App : Application(), DIAware {
    override val di = DI.lazy {
        import(AppModule.get(this@App))
    }

    override val diTrigger = DITrigger()
    override fun onCreate() {
        super.onCreate()

        diTrigger.trigger()
        //initTimber()
    }

//    private fun initTimber() {
//        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
//    }
}


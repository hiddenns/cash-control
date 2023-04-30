package com.cashcontrol.core.di

import android.app.Application
import androidx.room.Room
import com.cashcontrol.core.database.AppDatabase
import com.cashcontrol.domain.datasource.LocalDatasource
import org.kodein.di.*


fun localDatabase(app: Application) = DI.Module("Local-Database") {
    bind<AppDatabase>() with singleton {
        Room.databaseBuilder(
            app.applicationContext,
            AppDatabase::class.java, "cashcontrol-database"
        ).build()
    }
    bindSingleton { LocalDatasource(instance()) }
}

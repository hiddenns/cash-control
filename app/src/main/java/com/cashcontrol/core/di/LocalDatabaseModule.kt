package com.cashcontrol.core.di

import android.app.Application
import androidx.room.Room
import com.cashcontrol.core.database.AppDatabase
import com.cashcontrol.domain.datasource.LocalDatasource
import com.cashcontrol.domain.datasource.category.CategoryDataSource
import com.cashcontrol.domain.datasource.transaction.TransactionDataSource
import com.cashcontrol.domain.datasource.wallet.WalletDataSource
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.kodein.di.singleton


fun localDatabase(app: Application) = DI.Module("Local-Database") {
    bind<AppDatabase>() with singleton {
        Room.databaseBuilder(
            app.applicationContext,
            AppDatabase::class.java, "cashcontrol-database"
        )
            .addCallback(AppDatabase.CALLBACK)
            //.addTypeConverter(DefaultConverter(GsonParser(Gson())))
            .build()
    }
    bindSingleton { LocalDatasource(instance()) }
    bindSingleton { TransactionDataSource(instance()) }
    bindSingleton { WalletDataSource(instance()) }
    bindSingleton { CategoryDataSource(instance()) }

}

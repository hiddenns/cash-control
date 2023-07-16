package com.cashcontrol.domain.datasource.wallet

import com.cashcontrol.core.database.AppDatabase
import com.cashcontrol.data.room.entity.Wallet
import com.cashcontrol.domain.base.BaseDataSource
import com.cashcontrol.domain.dao.WalletDao
import kotlinx.coroutines.flow.Flow

class WalletDataSource(database: AppDatabase) : BaseDataSource(), IWalletDatasource {

    init {
        database.openHelper.writableDatabase
    }

    private var walletDao: WalletDao = database.walletDao()

    override fun addWallet(wallet: Wallet) = walletDao.insert(wallet)

    override fun getWallets(): Flow<List<Wallet>> = walletDao.getAll()


}
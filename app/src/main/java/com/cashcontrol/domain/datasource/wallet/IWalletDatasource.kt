package com.cashcontrol.domain.datasource.wallet

import com.cashcontrol.data.room.entity.Wallet
import kotlinx.coroutines.flow.Flow

interface IWalletDatasource {

    fun addWallet(wallet: Wallet)

    fun getWallets(): Flow<List<Wallet>>

}
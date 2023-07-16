package com.cashcontrol.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cashcontrol.data.room.entity.Wallet
import kotlinx.coroutines.flow.Flow

@Dao
interface WalletDao {

    @Query("SELECT * FROM wallet")
    fun getAll(): Flow<List<Wallet>>

    @Query("SELECT * FROM wallet WHERE walletId IN (:walletIds)")
    fun loadAllByIds(walletIds: List<Int>): Flow<List<Wallet>>

    @Insert
    fun insertAll(join: List<Wallet>)

    @Insert
    fun insert(join: Wallet)

    @Delete
    fun delete(wallet: Wallet)
}
package com.cashcontrol.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cashcontrol.data.room.entity.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query("SELECT * FROM `transaction`")
    fun getAll(): Flow<List<Transaction>>

    @Query("SELECT * FROM `transaction` WHERE transactionId IN (:transactionId)")
    fun loadAllByIds(transactionId: String): Flow<List<Transaction>>

    @Insert
    fun insertAll(join: List<Transaction>)

    @Insert
    fun insert(join: Transaction)

    @Delete
    fun delete(transaction: Transaction)
}
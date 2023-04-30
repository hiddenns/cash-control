package com.cashcontrol.domain.repositories.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cashcontrol.data.room.entity.PaymentMethod
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentMethodsDao {
    @Query("SELECT * FROM paymentmethod")
    fun getAll(): Flow<List<PaymentMethod>>

    @Query("SELECT * FROM paymentmethod WHERE uid IN (:paymentMethodIds)")
    fun loadAllByIds(paymentMethodIds: String): Flow<List<PaymentMethod>>

    @Insert
    fun insertAll(vararg paymentMethodIds: PaymentMethod)

    @Delete
    fun delete(paymentMethod: PaymentMethod)

}
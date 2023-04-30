package com.cashcontrol.data.model

import com.cashcontrol.data.room.entity.Transaction

data class PaymentMethod(
    val uid: String = "-1",
    val name: String = "default",
    val balance: Long = -1L,
    val transactions: List<String>? = emptyList(),
    val userId: String = "-1"
)
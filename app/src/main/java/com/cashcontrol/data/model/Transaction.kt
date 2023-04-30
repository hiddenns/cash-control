package com.cashcontrol.data.model

data class Transaction(
    val id: Long,
    val name: String,
    val sum: Int,
    val date: Int,
    val description: String,
    val categoryId: Long
)
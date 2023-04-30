package com.cashcontrol.data.model

sealed class Category {
    var id: Long = -1
    var name: String = ""
    val sum: String = ""
    var transactions: MutableList<Transaction> = mutableListOf()
    val image: String = ""

}
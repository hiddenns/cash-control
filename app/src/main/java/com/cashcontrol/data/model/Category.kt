package com.cashcontrol.data.model

sealed class Category {
    var id: Long = -1
    var name: String = ""
    val sum: String = ""
    var transactions: List<Transaction> = emptyList()
    val image: String = ""

    class Food() : Category()
    class Transport() : Category()
    class Entertainment() : Category()
    class House() : Category()
    class Health() : Category()
    class Gifts() : Category()
}
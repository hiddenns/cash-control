package com.cashcontrol.data.model

data class Action(
    val types: ActionTypes,
    val nameResource: Int,
    val sum: Int,
    val amountTransactions: Int,
    val color: Int,
    val imageResource: Int
)

enum class ActionTypes {
    EXPANSES, INCOME
}
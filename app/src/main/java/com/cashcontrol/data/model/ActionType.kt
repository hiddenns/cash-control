package com.cashcontrol.data.model

enum class ActionType {
    INCOME, CUMULATION, EXPENSE;

    override fun toString(): String {
        return this.name
    }
}
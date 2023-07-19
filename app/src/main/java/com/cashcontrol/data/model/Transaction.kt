package com.cashcontrol.data.model

data class Transaction(
    var transactionId: Int = -1,
    var name: String = "",
    var sum: Int = 0,
    var date: Long = 0,
    var description: String = "",
    var category: Category,
    var walletId: Long = -1,
    var type: ActionType = ActionType.EXPENSE
)
//INSERT INTO `Transaction`(name, sum, date, description, categoryId, walletId, type) VALUES ("first food", 0, 0, "", 1,  -1, "EXPENSE")
package com.cashcontrol.feature.main.dialogs

import com.cashcontrol.data.model.Transaction

interface ActionDialogSubmitCallBack {
    fun onClose(transaction: Transaction)
}
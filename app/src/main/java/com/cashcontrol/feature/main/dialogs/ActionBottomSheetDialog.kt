package com.cashcontrol.feature.main.dialogs

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.cashcontrol.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class ActionBottomSheetDialog(context: Context) : BottomSheetDialog(context) {


    var submitCallBack: ActionDialogSubmitCallBack? = null
    var etSum = findViewById<EditText>(R.id.et_enter_sum)
    var etDescription = findViewById<EditText>(R.id.et_enter_description)
    var category = null
    var tvTitle = findViewById<TextView>(R.id.tv_title)
    var btnSubmit = findViewById<Button>(R.id.btn_transaction_confirm)
    var parent = findViewById<ConstraintLayout>(R.id.action_dialog_parent)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        etSum = findViewById(R.id.et_enter_sum)
        etDescription = findViewById(R.id.et_enter_description)
        btnSubmit = findViewById(R.id.btn_transaction_confirm)
        tvTitle = findViewById(R.id.tv_title)
        parent = findViewById(R.id.action_dialog_parent)


        btnSubmit?.setOnClickListener {
//            val transaction = Transaction(
//                transactionId = "",
//                name = "name",
//                etSum?.text.toString().toInt(),
//                getCurrentTimeUtc(),
//                etDescription?.text.toString(),
//                Category()
//            )
//            submitCallBack?.onClose(transaction)
//            clearFields()
            dismiss()
        }


    }

    private fun clearFields() {
        etSum?.text?.clear()
        etDescription?.text?.clear()
    }

}
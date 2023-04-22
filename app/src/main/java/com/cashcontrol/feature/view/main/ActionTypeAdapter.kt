package com.cashcontrol.feature.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.cashcontrol.R
import com.cashcontrol.data.model.Action
import com.cashcontrol.data.model.ActionTypes

class ActionTypeAdapter : RecyclerView.Adapter<ActionTypeAdapter.ActionTypeViewHolder>() {

    class ActionTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivImage: ImageView = itemView.findViewById(R.id.action_image)
        private val tvSum: TextView = itemView.findViewById(R.id.action_sum)
        private val tvName: TextView = itemView.findViewById(R.id.action_name)
        private val tvAmountTransactions: TextView = itemView.findViewById(R.id.transactions_amount)
        private val clParent: ConstraintLayout = itemView.findViewById(R.id.parent)

        fun bind(position: Int) {
            with(actionsList[position]) {
                ivImage.setImageResource(this.imageResource)
                tvSum.text = itemView.context.getText(nameResource)
                tvName.text = sum.toString()
                tvAmountTransactions.text = amountTransactions.toString()
                clParent.setBackgroundColor(ResourcesCompat.getColor(itemView.resources, color, null))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionTypeViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.action_viewholder, parent, false)
        return ActionTypeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ActionTypeViewHolder, position: Int) {
        holder.bind(position % 2)
    }

    override fun getItemCount() = Int.MAX_VALUE

    companion object {
        val actionsList = listOf(
            Action(ActionTypes.EXPANSES, R.string.expanses, 300, 2, R.color.purple_200, org.kodein.di.android.support.R.drawable.abc_ab_share_pack_mtrl_alpha),
            Action(ActionTypes.INCOME, R.string.income,1200, 5,  R.color.teal_200, R.drawable.ic_launcher_foreground)
        )
    }

}
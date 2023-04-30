package com.cashcontrol.feature.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.cashcontrol.R

class ActionTypeAdapter(
    private var actionTypeList: MutableList<com.cashcontrol.data.room.entity.PaymentMethod>,
    private val clickCallBack: (position: Int) -> Unit
) : RecyclerView.Adapter<ActionTypeAdapter.ActionTypeViewHolder>() {

    inner class ActionTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val ivImage: ImageView = itemView.findViewById(R.id.action_image)
        private val tvSum: TextView = itemView.findViewById(R.id.action_sum)
        private val tvName: TextView = itemView.findViewById(R.id.action_name)
        private val tvAmountTransactions: TextView = itemView.findViewById(R.id.transactions_amount)
        private val clParent: ConstraintLayout = itemView.findViewById(R.id.parent)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(action: com.cashcontrol.data.room.entity.PaymentMethod) {
            with(action) {
//                ivImage.setImageResource(this.imageResource)
                tvSum.text = itemView.context.getText(name.toInt())
                tvName.text = balance.toString()
                tvAmountTransactions.text = transactions.count().toString()
                clParent.setBackgroundColor(
                    ResourcesCompat.getColor(
                        itemView.resources,
                        R.color.purple_500,
                        null
                    )
                )
            }
        }

        override fun onClick(v: View?) {
            clickCallBack.invoke(adapterPosition)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionTypeViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.action_viewholder, parent, false)

        return ActionTypeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ActionTypeViewHolder, position: Int) {
        holder.bind(actionTypeList[position])
    }

    override fun getItemCount() = actionTypeList.size

    fun updateData(actions: List<com.cashcontrol.data.room.entity.PaymentMethod>) {
        this.actionTypeList = actions as MutableList<com.cashcontrol.data.room.entity.PaymentMethod>
        notifyItemRangeInserted(actionTypeList.size.dec(), actionTypeList.size + actions.size - 2)
    }

}
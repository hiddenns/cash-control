package com.cashcontrol.feature.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cashcontrol.R
import com.cashcontrol.data.model.ActionType
import com.cashcontrol.data.model.Transaction
import com.cashcontrol.databinding.ViewHolderTransactionBinding
import com.cashcontrol.feature.extenstion.getImageDrawableByIdentifier

class TransactionListAdapter(private val list: MutableList<Transaction>) :
    RecyclerView.Adapter<TransactionListAdapter.TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val itemView = ViewHolderTransactionBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TransactionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun updateData(transactionList: List<Transaction>) {
        list.clear()
        list.addAll(transactionList)
        notifyDataSetChanged()
    }

    class TransactionViewHolder(private val binding: ViewHolderTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Transaction) {
            with(binding) {
                when (item.category.type) {
                    ActionType.INCOME -> {
                        tvSum.setTextColor(ContextCompat.getColor(itemView.context, R.color.aqua))
                    }
                    ActionType.EXPENSE -> {
                        tvSum.setTextColor(ContextCompat.getColor(itemView.context, R.color.pink))
                    }
                }

                tvTitle.text = item.description
                tvSum.text = "${item.sum} $"
                ivIcon.background = itemView.context.getImageDrawableByIdentifier(item.category.imageSource)
            }
        }
    }

}

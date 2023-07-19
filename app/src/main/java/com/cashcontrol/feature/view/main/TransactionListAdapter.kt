package com.cashcontrol.feature.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.cashcontrol.data.model.Transaction
import com.cashcontrol.databinding.ViewHolderTransactionBinding

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

    class TransactionViewHolder(val binding: ViewHolderTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Transaction) {
            with(binding) {
                tvTitle.text = item.description
                tvSum.text = "${item.sum} $"
                ivIcon.background = ResourcesCompat.getDrawable(itemView.resources, item.category.imageSource,null)
            }
        }
    }

}

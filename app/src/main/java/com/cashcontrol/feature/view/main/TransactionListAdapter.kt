package com.cashcontrol.feature.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cashcontrol.R
import com.cashcontrol.data.model.Transaction

class TransactionListAdapter(private val list: MutableList<Transaction>) :
    RecyclerView.Adapter<TransactionListAdapter.TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_transaction, parent, false)
        return TransactionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.text.text = list[position].name
    }

    override fun getItemCount() = list.size

    fun updateData(transactionList: List<Transaction>) {
        list.addAll(transactionList)
//        notifyItemRangeInserted(list.size - 1, list.size + transactionList.size - 2)
        notifyDataSetChanged()
    }

    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.tv_title)
    }

}

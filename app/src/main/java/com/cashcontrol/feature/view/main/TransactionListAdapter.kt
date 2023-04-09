package com.cashcontrol.feature.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cashcontrol.R

class TransactionListAdapter(private val list: List<String>) :
    RecyclerView.Adapter<TransactionListAdapter.TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_transaction, parent, false)
        return TransactionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.text.text = mock[position]
    }

    override fun getItemCount() = mock.size


    companion object {
        private val mock:List<String> = listOf("first","second","third", "four", "five", "six", "seven", "nine", "ten","first","second","third", "four", "five", "six", "seven", "nine", "ten",
            "first","second","third", "four", "five", "six", "seven", "nine",
            "ten", "first","second","third", "four", "five", "six", "seven", "nine", "ten").map {
                item -> "$item transaction"
        }
    }

    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.textVh)
    }

}

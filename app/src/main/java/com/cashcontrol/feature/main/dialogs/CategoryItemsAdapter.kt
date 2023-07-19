package com.cashcontrol.feature.main.dialogs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cashcontrol.data.model.Category
import com.cashcontrol.databinding.CategoryHolderBinding

class CategoryItemsAdapter(
    var items: List<Category>
) : RecyclerView.Adapter<CategoryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        val itemView = CategoryHolderBinding.inflate(
            LayoutInflater.from(parent.context)
        )
        return CategoryItemViewHolder(itemView)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun submit(list: List<Category>) {
        items = list
        notifyDataSetChanged()
    }
}
package com.cashcontrol.feature.main.dialogs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.cashcontrol.data.model.Category
import com.cashcontrol.databinding.CategoryHolderBinding

class CategoryItemsAdapter :
    ListAdapter<Category, CategoryItemViewHolder>(CategoryDiffCallBack()) {

    var tracker: SelectionTracker<Long>? = null

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        val itemView = CategoryHolderBinding.inflate(
            LayoutInflater.from(parent.context)
        )
        return CategoryItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        tracker?.let {
            holder.bind(currentList[position], it.isSelected(position.toLong()))
        }
    }

    override fun getItemId(position: Int) = position.toLong()

    private class CategoryDiffCallBack : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean =
            oldItem.categoryId == newItem.categoryId

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean =
            oldItem == newItem
    }
}
package com.cashcontrol.feature.main.dialogs

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cashcontrol.data.model.Category
import com.cashcontrol.databinding.CategoryHolderBinding

class CategoryItemViewHolder(
    private val binding: CategoryHolderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Category) {
        with(binding) {
            tvName.text = item.name
            item.imageSource.let { src ->
                tvName.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    ContextCompat.getDrawable(itemView.context, src),
                    null,
                    null
                )
            }
        }
    }
}
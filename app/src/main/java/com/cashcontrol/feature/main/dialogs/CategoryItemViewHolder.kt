package com.cashcontrol.feature.main.dialogs

import androidx.core.content.ContextCompat
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import com.cashcontrol.R
import com.cashcontrol.data.model.Category
import com.cashcontrol.databinding.CategoryHolderBinding
import com.cashcontrol.feature.extenstion.getImageDrawableByIdentifier

class CategoryItemViewHolder(
    private val binding: CategoryHolderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Category, isActivated: Boolean = false) {
        setBackground(isActivated)
        with(binding) {
            tvName.text = item.name

            if (item.categoryId == -1L) {
                tvName.text = "add"
                tvName.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    ContextCompat.getDrawable(itemView.context, R.drawable.icon_globus),
                    null,
                    null
                )
                return
            }

            item.imageSource.let { src ->
                tvName.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    itemView.context.getImageDrawableByIdentifier(src),
                    null,
                    null
                )
            }

        }
    }

    private fun setBackground(isActivated: Boolean) {
        with(binding) {
            if (isActivated) {
                container.background = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.bg_item_category_selectable
                )
                tvName.setTextColor(ContextCompat.getColor(itemView.context, R.color.aqua))
            } else {
                container.background =
                    ContextCompat.getDrawable(itemView.context, R.drawable.bg_item_category)
                tvName.setTextColor(ContextCompat.getColor(itemView.context, R.color.main_white))
            }
        }
    }

    fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
        object : ItemDetailsLookup.ItemDetails<Long>() {
            override fun getPosition(): Int = adapterPosition
            override fun getSelectionKey(): Long = itemId
        }
}
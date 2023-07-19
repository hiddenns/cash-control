package com.cashcontrol.core.database

import android.content.Context
import androidx.core.content.res.ResourcesCompat
import com.cashcontrol.R
import com.cashcontrol.data.model.ActionType
import com.cashcontrol.data.room.entity.Category

class DefaultDatabaseConfig {

    companion object {
        fun getCategoriesDefault(context: Context): List<Category> = listOf(
            Category().apply {
                name = context.getString(R.string.transport)
                imageSource = R.drawable.icon_bus
                type = ActionType.EXPENSE
                color = ResourcesCompat.getColor(context.resources, R.color.blue, null)
            },
            Category().apply {
                name = context.getString(R.string.food)
                imageSource = R.drawable.icon_bottle
                type = ActionType.EXPENSE
                color = ResourcesCompat.getColor(context.resources, R.color.beige, null)
            }
        )
    }
}
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
                imageSource = R.drawable.icon_cart
                type = ActionType.EXPENSE
                color = ResourcesCompat.getColor(context.resources, R.color.beige, null)
            },
            Category().apply {
                name = context.getString(R.string.entertainment)
                imageSource = R.drawable.icon_gamepad
                type = ActionType.EXPENSE
                color = ResourcesCompat.getColor(context.resources, R.color.beige, null)
            },
            Category().apply {
                name = context.getString(R.string.house)
                imageSource = R.drawable.icon_home_1
                type = ActionType.EXPENSE
                color = ResourcesCompat.getColor(context.resources, R.color.beige, null)
            },
            Category().apply {
                name = context.getString(R.string.communications)
                imageSource = R.drawable.icon_incoming_call_rounded
                type = ActionType.EXPENSE
                color = ResourcesCompat.getColor(context.resources, R.color.beige, null)
            },
            Category().apply {
                name = context.getString(R.string.clothes)
                imageSource = R.drawable.icon_t_shirt
                type = ActionType.EXPENSE
                color = ResourcesCompat.getColor(context.resources, R.color.beige, null)
            },
            Category().apply {
                name = context.getString(R.string.gifts)
                imageSource = R.drawable.icon_user_heart_rounded
                type = ActionType.EXPENSE
                color = ResourcesCompat.getColor(context.resources, R.color.beige, null)
            },
            Category().apply {
                name = context.getString(R.string.bills)
                imageSource = R.drawable.icon_tag_price
                type = ActionType.EXPENSE
                color = ResourcesCompat.getColor(context.resources, R.color.beige, null)
            },
            Category().apply {
                name = context.getString(R.string.car)
                imageSource = R.drawable.icon_fuel
                type = ActionType.EXPENSE
                color = ResourcesCompat.getColor(context.resources, R.color.beige, null)
            },
            Category().apply {
                name = context.getString(R.string.health)
                imageSource = R.drawable.icon_heart_pulse
                type = ActionType.EXPENSE
                color = ResourcesCompat.getColor(context.resources, R.color.beige, null)
            },
            Category().apply {
                name = context.getString(R.string.pets)
                imageSource = R.drawable.icon_paw
                type = ActionType.EXPENSE
                color = ResourcesCompat.getColor(context.resources, R.color.beige, null)
            },
            //income
            Category().apply {
                name = context.getString(R.string.deposits)
                imageSource = R.drawable.icon_dollar_minimalistic
                type = ActionType.INCOME
                color = ResourcesCompat.getColor(context.resources, R.color.beige, null)
            },
            Category().apply {
                name = context.getString(R.string.salary)
                imageSource = R.drawable.icon_wad_of_money
                type = ActionType.INCOME
                color = ResourcesCompat.getColor(context.resources, R.color.beige, null)
            },
            Category().apply {
                name = context.getString(R.string.savings)
                imageSource = R.drawable.icon_money_bag
                type = ActionType.INCOME
                color = ResourcesCompat.getColor(context.resources, R.color.beige, null)
            },
        )
    }
}
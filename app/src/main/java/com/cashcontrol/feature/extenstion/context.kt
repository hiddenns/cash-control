package com.cashcontrol.feature.extenstion

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

fun Context.getImageDrawableByIdentifier(src: String): Drawable? {
    val id = resources.getIdentifier(src, "drawable", packageName)
    return ContextCompat.getDrawable(this, id)
}
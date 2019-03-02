package com.krinitsyn.gitgist.ui.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.appcompat.content.res.AppCompatResources

internal fun Context.drawableAttr(@AttrRes resId: Int): Drawable? {
    val typedValue = TypedValue()
    this.theme.resolveAttribute(resId, typedValue, true)
    return AppCompatResources.getDrawable(this, typedValue.resourceId)
}
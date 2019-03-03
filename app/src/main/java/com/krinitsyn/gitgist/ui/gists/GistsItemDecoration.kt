package com.krinitsyn.gitgist.ui.gists

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.Px
import androidx.recyclerview.widget.RecyclerView
import com.krinitsyn.gitgist.R
import com.krinitsyn.gitgist.ui.util.drawableAttr
import org.jetbrains.anko.dimenAttr

internal class GistsItemDecoration : RecyclerView.ItemDecoration() {

    private lateinit var attrs: Attrs

    override fun getItemOffsets(outRect: Rect, itemView: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, itemView, parent, state)
        ensureAttrs(parent.context)

        val viewHolder = parent.findContainingViewHolder(itemView)
        viewHolder?.let {
            if (viewHolder.adapterPosition > RecyclerView.NO_POSITION) {
                val listDividerHeight = attrs.listDividerHeight ?: 0
                if (listDividerHeight != 0) {
                    outRect.set(0, listDividerHeight, 0, 0)
                }
            }
        }
    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)
        ensureAttrs(parent.context)

        for (index in 0 until parent.childCount) {
            val child = parent.getChildAt(index)
            val viewHolder = parent.getChildViewHolder(child)
            val listDivider = attrs.listDivider
            val listDividerHeight = attrs.listDividerHeight ?: 0
            if (viewHolder != null && listDivider != null && listDividerHeight > 0) {
                listDivider.setBounds(child.left, child.top - listDividerHeight, child.right, child.top)
                listDivider.draw(canvas)
            }
        }
    }

    private fun ensureAttrs(context: Context) {
        attrs = Attrs(
            listDivider = context.drawableAttr(android.R.attr.listDivider),
            listDividerHeight = context.dimenAttr(R.attr.listDividerHeight)
        )
    }

    private data class Attrs(
        val listDivider: Drawable? = null,
        @Px val listDividerHeight: Int? = null
    )

}
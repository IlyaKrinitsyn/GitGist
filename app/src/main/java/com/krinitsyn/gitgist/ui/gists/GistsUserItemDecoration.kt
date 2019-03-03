package com.krinitsyn.gitgist.ui.gists

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.Px
import androidx.recyclerview.widget.RecyclerView
import com.krinitsyn.gitgist.R
import org.jetbrains.anko.dimenAttr

class GistsUserItemDecoration : RecyclerView.ItemDecoration() {

    private lateinit var attrs: Attrs

    override fun getItemOffsets(outRect: Rect, itemView: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, itemView, parent, state)
        ensureAttrs(parent.context)

        val viewHolder = parent.findContainingViewHolder(itemView)
        viewHolder?.let {
            if (viewHolder.adapterPosition > RecyclerView.NO_POSITION) {
                val marginXSmall = attrs.marginXSmall ?: 0
                val marginSmall = attrs.marginSmall ?: 0
                outRect.set(marginXSmall, marginSmall, marginXSmall, marginSmall)
            }
        }
    }

    private fun ensureAttrs(context: Context) {
        attrs = Attrs(
            marginXSmall = context.dimenAttr(R.attr.marginXSmall),
            marginSmall = context.dimenAttr(R.attr.marginSmall)
        )
    }

    private data class Attrs(
        @Px val marginXSmall: Int? = null,
        @Px val marginSmall: Int? = null
    )

}
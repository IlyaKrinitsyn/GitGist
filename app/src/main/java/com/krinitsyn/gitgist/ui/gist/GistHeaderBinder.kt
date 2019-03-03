package com.krinitsyn.gitgist.ui.gist

import com.krinitsyn.gitgist.presentation.gist.GistViewState
import com.krinitsyn.gitgist.ui.viewholder.HeaderViewHolder

internal object GistHeaderBinder {

    fun bind(viewHolder: HeaderViewHolder, header: GistViewState.Item.Header) = with(viewHolder) {
        value = itemView.context.getString(header.value)
    }

}
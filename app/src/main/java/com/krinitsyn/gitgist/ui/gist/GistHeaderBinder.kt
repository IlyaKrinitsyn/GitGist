package com.krinitsyn.gitgist.ui.gist

import com.krinitsyn.gitgist.presentation.gist.GistViewState

internal object GistHeaderBinder {

    fun bind(viewHolder: GistHeaderViewHolder, header: GistViewState.Item.Header) = with(viewHolder) {
        value = itemView.context.getString(header.value)
    }

}
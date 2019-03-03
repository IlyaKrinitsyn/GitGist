package com.krinitsyn.gitgist.ui.gist

import com.krinitsyn.gitgist.presentation.gist.GistViewState

internal object GistFileBinder {

    fun bind(viewHolder: GistFileViewHolder, file: GistViewState.Item.File) = with(viewHolder) {
        name = file.name
        content = file.content
    }

}
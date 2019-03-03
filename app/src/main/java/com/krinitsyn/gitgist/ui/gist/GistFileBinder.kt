package com.krinitsyn.gitgist.ui.gist

import com.krinitsyn.gitgist.presentation.gist.GistViewState
import com.krinitsyn.gitgist.ui.viewholder.FileViewHolder

internal object GistFileBinder {

    fun bind(viewHolder: FileViewHolder, file: GistViewState.Item.File) = with(viewHolder) {
        name = file.name
        content = file.content
    }

}
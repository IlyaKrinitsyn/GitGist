package com.krinitsyn.gitgist.ui.gist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krinitsyn.gitgist.R
import kotlinx.android.synthetic.main.list_item_gist_header.view.*

internal class GistHeaderViewHolder private constructor(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    companion object {

        internal fun inflate(layoutInflater: LayoutInflater, parent: ViewGroup) =
            GistHeaderViewHolder(layoutInflater.inflate(R.layout.list_item_gist_header, parent, false))

    }

    var value: CharSequence?
        get() = itemView.listItemGistHeaderValue.text
        set(value) {
            itemView.listItemGistHeaderValue.text = value
        }

}
package com.krinitsyn.gitgist.ui.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krinitsyn.gitgist.R
import kotlinx.android.synthetic.main.list_item_header.view.*

internal class HeaderViewHolder private constructor(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    companion object {

        internal fun inflate(layoutInflater: LayoutInflater, parent: ViewGroup) =
            HeaderViewHolder(layoutInflater.inflate(R.layout.list_item_header, parent, false))

    }

    var value: CharSequence?
        get() = itemView.listItemHeaderValue.text
        set(value) {
            itemView.listItemHeaderValue.text = value
        }

}
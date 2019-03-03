package com.krinitsyn.gitgist.ui.gists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.krinitsyn.gitgist.R
import kotlinx.android.synthetic.main.list_item_gist.view.*

internal class GistViewHolder private constructor(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    companion object {

        internal fun inflate(layoutInflater: LayoutInflater, parent: ViewGroup) =
            GistViewHolder(layoutInflater.inflate(R.layout.list_item_gist, parent, false))

    }

    private var onClickListener: ((RecyclerView.ViewHolder, View) -> Unit)? = null

    init {
        itemView.setOnClickListener(::onClick)
    }

    fun setOnClickListenet(listener: ((RecyclerView.ViewHolder, View) -> Unit)?) {
        onClickListener = listener
        itemView.isClickable = listener != null
    }

    fun setAvatar(viewConsumer: (ImageView) -> Unit) {
        viewConsumer(itemView.listItemGistAvatarView)
    }

    var gistName: CharSequence?
        get() = itemView.listItemGistNameView.text
        set(value) {
            itemView.listItemGistNameView.text = value
        }

    var gistDescription: CharSequence?
        get() = itemView.listItemGistDescriptionView.text
        set(value) {
            itemView.listItemGistDescriptionView.text = value
        }

    var isGistDescriptionVisible: Boolean
        get() = itemView.listItemGistDescriptionView.isVisible
        set(value) {
            itemView.listItemGistDescriptionView.isVisible = value
        }

    private fun onClick(view: View) {
        onClickListener?.invoke(this, view)
    }

}
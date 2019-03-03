package com.krinitsyn.gitgist.ui.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.krinitsyn.gitgist.R
import kotlinx.android.synthetic.main.list_item_user.view.*

internal class UserViewHolder private constructor(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    companion object {

        internal fun inflate(layoutInflater: LayoutInflater, parent: ViewGroup) =
            UserViewHolder(layoutInflater.inflate(R.layout.list_item_user, parent, false))

    }

    private var onClickListener: ((RecyclerView.ViewHolder, View) -> Unit)? = null

    init {
        itemView.setOnClickListener(::onClick)
    }

    fun setOnClickListener(listener: ((RecyclerView.ViewHolder, View) -> Unit)?) {
        onClickListener = listener
        itemView.isClickable = listener != null
    }

    fun setAvatar(viewConsumer: (ImageView) -> Unit) {
        viewConsumer(itemView.listItemUserAvatar)
    }

    var gists: CharSequence?
        get() = itemView.listItemUserGists.text
        set(value) {
            itemView.listItemUserGists.text = value
        }

    private fun onClick(view: View) {
        onClickListener?.invoke(this, view)
    }

}
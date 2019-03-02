package com.krinitsyn.gitgist.ui.gists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.krinitsyn.git_gist.ImageLoader
import com.krinitsyn.gitgist.R
import com.krinitsyn.gitgist.presentation.gists.GistsViewState
import kotlinx.android.synthetic.main.list_item_gist.view.*
import kotlin.properties.Delegates

internal class GistsAdapter(
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<GistViewHolder>() {

    var items: List<GistsViewState.Gist> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GistViewHolder.inflate(layoutInflater, parent)
    }

    override fun onBindViewHolder(viewHolder: GistViewHolder, position: Int) {
        val context = viewHolder.itemView.context
        val gist = items[position]
        with(viewHolder) {
            setAvatar { view -> imageLoader.loadUserAvatar(view, gist.avatarUrl) }
            gistName = context.getString(R.string.fragment_gists_gist_name_format, gist.login, gist.gistName)
            gist.gistDescription?.let { description ->
                gistDescription = description
                isGistDescriptionVisible = true
            }
        }
    }

    override fun getItemCount(): Int = items.size

}

internal class GistViewHolder(
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
        viewConsumer(itemView.avatarView)
    }

    var gistName: CharSequence?
        get() = itemView.gistNameView.text
        set(value) {
            itemView.gistNameView.text = value
        }

    var gistDescription: CharSequence?
        get() = itemView.gistDescriptionView.text
        set(value) {
            itemView.gistDescriptionView.text = value
        }

    var isGistDescriptionVisible: Boolean
        get() = itemView.gistDescriptionView.isVisible
        set(value) {
            itemView.gistDescriptionView.isVisible = value
        }

    private fun onClick(view: View) {
        onClickListener?.invoke(this, view)
    }

}
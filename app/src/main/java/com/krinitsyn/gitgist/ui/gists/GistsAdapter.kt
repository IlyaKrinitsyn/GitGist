package com.krinitsyn.gitgist.ui.gists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krinitsyn.git_gist.ImageLoader
import com.krinitsyn.gitgist.R
import com.krinitsyn.gitgist.presentation.gists.GistsViewState
import kotlin.properties.Delegates

internal class GistsAdapter(
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<GistViewHolder>() {

    var onClickListener: ((GistsViewState.Gist) -> Unit)? = null

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
            setAvatar { view -> imageLoader.loadUserAvatar40dp(view, gist.avatarUrl) }
            gistName = context.getString(R.string.fragment_gists_gist_name_format, gist.login, gist.gistName)
            gist.gistDescription?.let { description ->
                gistDescription = description
                isGistDescriptionVisible = true
                setOnClickListenet { viewHolder, _ -> onGistClick(viewHolder as GistViewHolder) }
            }
        }
    }

    override fun getItemCount(): Int = items.size

    private fun onGistClick(viewHolder: GistViewHolder) {
        val position = viewHolder.adapterPosition
        if (position in 0..(itemCount - 1)) {
            val gist = items[position]
            onClickListener?.invoke(gist)
        }
    }

}
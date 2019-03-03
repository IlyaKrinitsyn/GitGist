package com.krinitsyn.gitgist.ui.gist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krinitsyn.gitgist.presentation.gist.GistViewState.Item
import kotlin.properties.Delegates

internal class GistAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<Item> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type.value
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            Item.Type.Header.value -> GistHeaderViewHolder.inflate(inflater, parent)
            Item.Type.File.value -> GistFileViewHolder.inflate(inflater, parent)
            Item.Type.Commit.value -> GistCommitViewHolder.inflate(inflater, parent)
            else -> throw IllegalStateException("Unknown view type = $viewType")
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is Item.Header -> GistHeaderBinder.bind(viewHolder as GistHeaderViewHolder, item)
            is Item.File -> GistFileBinder.bind(viewHolder as GistFileViewHolder, item)
            is Item.Commit -> GistCommitBinder.bind(viewHolder as GistCommitViewHolder, item)
        }
    }

}
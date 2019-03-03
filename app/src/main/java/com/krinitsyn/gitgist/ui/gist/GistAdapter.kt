package com.krinitsyn.gitgist.ui.gist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krinitsyn.gitgist.presentation.gist.GistViewState.Item
import com.krinitsyn.gitgist.ui.viewholder.CommitViewHolder
import com.krinitsyn.gitgist.ui.viewholder.FileViewHolder
import com.krinitsyn.gitgist.ui.viewholder.HeaderViewHolder
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
            Item.Type.Header.value -> HeaderViewHolder.inflate(inflater, parent)
            Item.Type.File.value -> FileViewHolder.inflate(inflater, parent)
            Item.Type.Commit.value -> CommitViewHolder.inflate(inflater, parent)
            else -> throw IllegalStateException("Unknown view type = $viewType")
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is Item.Header -> GistHeaderBinder.bind(viewHolder as HeaderViewHolder, item)
            is Item.File -> GistFileBinder.bind(viewHolder as FileViewHolder, item)
            is Item.Commit -> GistCommitBinder.bind(viewHolder as CommitViewHolder, item)
        }
    }

}
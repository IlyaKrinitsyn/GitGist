package com.krinitsyn.gitgist.ui.gist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krinitsyn.gitgist.R
import kotlinx.android.synthetic.main.list_item_gist_commit.view.*

internal class GistCommitViewHolder private constructor(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    companion object {

        internal fun inflate(layoutInflater: LayoutInflater, parent: ViewGroup) =
            GistCommitViewHolder(layoutInflater.inflate(R.layout.list_item_gist_commit, parent, false))

    }

    var commitId: CharSequence?
        get() = itemView.listItemGistCommitIdView.text
        set(value) {
            itemView.listItemGistCommitIdView.text = value
        }

    var user: CharSequence?
        get() = itemView.listItemGistCommitUserView.text
        set(value) {
            itemView.listItemGistCommitUserView.text = value
        }

    var additions: CharSequence?
        get() = itemView.listItemGistCommitAdditionsView.text
        set(value) {
            itemView.listItemGistCommitAdditionsView.text = value
        }

    var deletions: CharSequence?
        get() = itemView.listItemGistCommitDeletionsView.text
        set(value) {
            itemView.listItemGistCommitDeletionsView.text = value
        }

}
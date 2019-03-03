package com.krinitsyn.gitgist.ui.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.krinitsyn.gitgist.R
import kotlinx.android.synthetic.main.list_item_commit.view.*

internal class CommitViewHolder private constructor(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    companion object {

        internal fun inflate(layoutInflater: LayoutInflater, parent: ViewGroup) =
            CommitViewHolder(layoutInflater.inflate(R.layout.list_item_commit, parent, false))

    }

    var commitId: CharSequence?
        get() = itemView.listItemCommitIdView.text
        set(value) {
            itemView.listItemCommitIdView.text = value
        }

    var user: CharSequence?
        get() = itemView.listItemCommitUserView.text
        set(value) {
            itemView.listItemCommitUserView.text = value
        }

    var isUserVisible: Boolean
        get() = itemView.listItemCommitUserView.isVisible
        set(value) {
            itemView.listItemCommitUserView.isVisible = value
        }

    var additions: CharSequence?
        get() = itemView.listItemCommitAdditionsView.text
        set(value) {
            itemView.listItemCommitAdditionsView.text = value
        }

    var deletions: CharSequence?
        get() = itemView.listItemCommitDeletionsView.text
        set(value) {
            itemView.listItemCommitDeletionsView.text = value
        }

}
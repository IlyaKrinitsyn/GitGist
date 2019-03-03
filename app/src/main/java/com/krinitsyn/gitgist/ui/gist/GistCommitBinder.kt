package com.krinitsyn.gitgist.ui.gist

import com.krinitsyn.gitgist.R
import com.krinitsyn.gitgist.presentation.gist.GistViewState

internal object GistCommitBinder {

    fun bind(viewHolder: GistCommitViewHolder, commit: GistViewState.Item.Commit) = with(viewHolder) {
        commitId = commit.version
        val context = viewHolder.itemView.context
        user = context.getString(R.string.list_item_gist_commit_user_format, commit.userName)
        additions = context.getString(R.string.list_item_gist_commit_additions_format, commit.changeStatus.additions)
        deletions = context.getString(R.string.list_item_gist_commit_deletions_format, commit.changeStatus.deletions)
    }

}
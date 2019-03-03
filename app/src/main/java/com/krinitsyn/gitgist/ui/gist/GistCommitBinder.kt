package com.krinitsyn.gitgist.ui.gist

import com.krinitsyn.gitgist.R
import com.krinitsyn.gitgist.presentation.gist.GistViewState
import com.krinitsyn.gitgist.ui.viewholder.CommitViewHolder

internal object GistCommitBinder {

    fun bind(viewHolder: CommitViewHolder, commit: GistViewState.Item.Commit) = with(viewHolder) {
        commitId = commit.version
        val context = viewHolder.itemView.context
        commit.userName?.let { userName ->
            user = context.getString(R.string.list_item_commit_user_format, userName)
            isUserVisible = true
        }
        additions = context.getString(R.string.list_item_commit_additions_format, commit.changeStatus.additions)
        deletions = context.getString(R.string.list_item_commit_deletions_format, commit.changeStatus.deletions)
    }

}
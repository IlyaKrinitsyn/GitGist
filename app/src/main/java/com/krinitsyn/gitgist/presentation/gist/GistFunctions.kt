package com.krinitsyn.gitgist.presentation.gist

import com.krinitsyn.git_gist.data.Commit
import com.krinitsyn.git_gist.data.File
import com.krinitsyn.git_gist.data.Gist
import com.krinitsyn.gitgist.R
import com.krinitsyn.gitgist.presentation.gist.GistViewState.Info
import com.krinitsyn.gitgist.presentation.gist.GistViewState.Item

internal object GistFunctions {

    fun createInfo(gist: Gist): Info = Info(
        login = gist.owner.login,
        avatarUrl = gist.owner.avatarUrl,
        userUrl = gist.owner.url,
        gistName = gist.files.keys.first(),
        gistDescription = gist.description
    )

    fun createItems(gist: Gist, rawCommits: List<Commit>): List<Item> {
        val items = mutableListOf<Item>()

        val files = gist.files.map(::createFile)
        if (files.isNotEmpty()) {
            items += Item.Header(R.string.list_item_gist_header_files)
            items += files
        }

        val commits = rawCommits.map(::createCommit)
        if (commits.isNotEmpty()) {
            items += Item.Header(R.string.list_item_gist_header_commits)
            items += commits
        }

        return items
    }

    private fun createFile(fileEntry: Map.Entry<String, File>): Item.File = Item.File(
        name = fileEntry.key,
        content = fileEntry.value.content
            ?: throw IllegalStateException("File's content should be not null"),
        rawUrl = fileEntry.value.rawUrl
    )

    private fun createCommit(commit: Commit): Item.Commit = Item.Commit(
        userName = commit.user.login,
        version = commit.version,
        changeStatus = commit.changeStatus
    )

}
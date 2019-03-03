package com.krinitsyn.git_gist.data.external

import com.krinitsyn.git_gist.data.*

internal object GithubGistTransformers {

    fun gists(gistExtList: List<GistExt>): List<Gist> = gistExtList.map(::gist)

    fun gist(gistExt: GistExt): Gist = Gist(
        url = gistExt.url,
        forksUrl = gistExt.forksUrl,
        commitsUrl = gistExt.commitsUrl,
        id = gistExt.id,
        files = gistExt.files.mapValues { entry -> file(entry.value) },
        description = gistExt.description,
        comments = gistExt.comments,
        commentsUrl = gistExt.commentsUrl,
        owner = user(gistExt.owner),
        truncated = gistExt.truncated
    )

    fun commits(commitExtList: List<CommitExt>): List<Commit> = commitExtList.map(::commit)

    private fun file(fileExt: FileExt): File = File(
        fileName = fileExt.fileName,
        type = fileExt.type,
        language = fileExt.language,
        rawUrl = fileExt.rawUrl,
        size = fileExt.size,
        content = fileExt.content
    )

    private fun commit(commitExt: CommitExt): Commit = Commit(
        user = commitExt.user?.let(::user),
        version = commitExt.version,
        committedAt = commitExt.committedAt,
        changeStatus = changeStatus(commitExt.changeStatus),
        url = commitExt.url
    )

    private fun user(userExt: UserExt): User = User(
        login = userExt.login,
        id = userExt.id,
        avatarUrl = userExt.avatarUrl,
        url = userExt.url,
        gistsUrl = userExt.gistsUrl
    )

    private fun changeStatus(changeStatusExt: ChangeStatusExt): ChangeStatus = ChangeStatus(
        total = changeStatusExt.total,
        additions = changeStatusExt.additions,
        deletions = changeStatusExt.deletions
    )

}
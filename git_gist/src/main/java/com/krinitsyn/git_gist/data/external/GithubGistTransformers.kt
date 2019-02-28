package com.krinitsyn.git_gist.data.external

import com.krinitsyn.git_gist.data.Gist
import com.krinitsyn.git_gist.data.User

internal object GithubGistTransformers {

    fun gists(gistExtList: List<GistExt>): List<Gist> = gistExtList.map(::gist)

    private fun gist(gistExt: GistExt): Gist = Gist(
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

    private fun file(fileExt: GistExt.FileExt): Gist.File = Gist.File(
        fileName = fileExt.fileName,
        type = fileExt.type,
        language = fileExt.language,
        rawUrl = fileExt.rawUrl,
        size = fileExt.size
    )


    private fun user(userExt: UserExt): User = User(
        login = userExt.login,
        id = userExt.id,
        avatarUrl = userExt.avatarUrl,
        url = userExt.url,
        gistsUrl = userExt.gistsUrl
    )

}
package com.krinitsyn.gitgist.presentation.gists

import com.krinitsyn.git_gist.data.Gist

internal object GistsFunctions {

    fun createGists(rawGists: List<Gist>): List<GistsViewState.Gist> = rawGists
        .map(::createGist)

    private fun createGist(rawGist: Gist): GistsViewState.Gist = GistsViewState.Gist(
        login = rawGist.owner.login,
        userId = rawGist.owner.id,
        avatarUrl = rawGist.owner.avatarUrl,
        gistName = rawGist.files.keys.first(),
        gistDescription = rawGist.description,
        gistId = rawGist.id
    )

}
package com.krinitsyn.gitgist.presentation.gists

import com.krinitsyn.git_gist.data.Gist
import com.krinitsyn.git_gist.data.User

internal object GistsFunctions {

    fun createUsers(rawGists: List<Gist>): List<GistsViewState.User> = rawGists
        .groupBy { gist -> gist.owner }
        .mapValues { entry -> entry.value.size }
        .toList()
        .sortedByDescending { (_, numberOfGists) -> numberOfGists }
        .map { (user, _) -> user }
        .map(::createUser)

    private fun createUser(rawUser: User): GistsViewState.User = GistsViewState.User(
        id = rawUser.id,
        login = rawUser.login,
        avatarUrl = rawUser.avatarUrl
    )

    fun createGists(rawGists: List<Gist>): List<GistsViewState.Gist> = rawGists
        .map(::createGist)

    private fun createGist(rawGist: Gist): GistsViewState.Gist = GistsViewState.Gist(
        login = rawGist.owner.login,
        avatarUrl = rawGist.owner.avatarUrl,
        gistName = rawGist.files.keys.first(),
        gistDescription = rawGist.description,
        gistId = rawGist.id
    )

}
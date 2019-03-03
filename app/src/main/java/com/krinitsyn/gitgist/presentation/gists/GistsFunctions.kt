package com.krinitsyn.gitgist.presentation.gists

import com.krinitsyn.git_gist.data.Gist
import com.krinitsyn.git_gist.data.User

internal object GistsFunctions {

    private const val UsersLimit = 10

    fun createUsers(rawGists: List<Gist>): List<GistsViewState.User> = rawGists
        .groupBy { gist -> gist.owner }
        .mapValues { entry -> entry.value.size }
        .toList()
        .sortedByDescending { (_, numberOfGists) -> numberOfGists }
        .take(UsersLimit)
        .map { (user, gists) -> createUser(user, gists) }

    private fun createUser(rawUser: User, gists: Int): GistsViewState.User = GistsViewState.User(
        id = rawUser.id,
        login = rawUser.login,
        avatarUrl = rawUser.avatarUrl,
        gists = gists
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
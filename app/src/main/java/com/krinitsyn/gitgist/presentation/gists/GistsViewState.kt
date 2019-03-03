package com.krinitsyn.gitgist.presentation.gists

import com.krinitsyn.utils.optional.None
import com.krinitsyn.utils.optional.Optional
import com.krinitsyn.utils.resource.Resource

internal data class GistsViewState(
    val users: Optional<List<User>> = None,
    val gists: Resource<List<Gist>> = Resource.Loading()
) {

    data class User(
        val id: Long,
        val login: String,
        val avatarUrl: String,
        val gists: Int
    )

    data class Gist(
        val login: String,
        val avatarUrl: String,
        val gistName: String,
        val gistDescription: String?,
        val gistId: String
    )

}
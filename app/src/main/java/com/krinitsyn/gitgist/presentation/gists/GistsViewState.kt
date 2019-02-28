package com.krinitsyn.gitgist.presentation.gists

import com.krinitsyn.utils.resource.Resource

data class GistsViewState(
        val gists: Resource<List<Gist>>
) {

    data class Gist(
            val login: String,
            val userId: Long,
            val avatarUrl: String,
            val gistName: String,
            val gistDescription: String,
            val gistId: String
    )

}
package com.krinitsyn.gitgist.presentation.gists

import com.krinitsyn.utils.optional.Optional
import com.krinitsyn.utils.resource.Resource

internal object GistsPartialViewStates {

    fun users(users: Optional<List<GistsViewState.User>>) = { oldViewState: GistsViewState ->
        oldViewState.copy(users = users)
    }

    fun gists(gists: Resource<List<GistsViewState.Gist>>) = { oldViewState: GistsViewState ->
        oldViewState.copy(gists = gists)
    }

}
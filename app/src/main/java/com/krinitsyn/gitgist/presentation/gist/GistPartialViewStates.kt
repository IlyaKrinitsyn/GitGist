package com.krinitsyn.gitgist.presentation.gist

import com.krinitsyn.utils.resource.Resource

internal object GistPartialViewStates {

    fun info(info: Resource<GistViewState.Info>) = { oldViewState: GistViewState ->
        oldViewState.copy(info = info)
    }

    fun items(items: Resource<List<GistViewState.Item>>) = { oldViewState: GistViewState ->
        oldViewState.copy(items = items)
    }

}
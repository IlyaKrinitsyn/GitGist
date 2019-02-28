package com.krinitsyn.gitgist.presentation

internal object PartialViewState {

    fun <ViewState> apply(): (ViewState, (ViewState) -> ViewState) -> ViewState =
            { viewState, partialCombiner -> partialCombiner(viewState) }

}
package com.krinitsyn.gitgist.presentation

internal typealias ViewStateReducer<ViewState> = (ViewState) -> ViewState

internal object PartialViewState {

    fun <ViewState : Any> apply(): (ViewState, ViewStateReducer<ViewState>) -> ViewState =
        { viewState, reducer -> reducer(viewState) }

}
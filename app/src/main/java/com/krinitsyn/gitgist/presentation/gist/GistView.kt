package com.krinitsyn.gitgist.presentation.gist

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

internal interface GistView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun onViewStateChanged(viewState: GistViewState)

}
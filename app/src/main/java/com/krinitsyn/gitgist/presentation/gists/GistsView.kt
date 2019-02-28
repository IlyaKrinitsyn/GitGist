package com.krinitsyn.gitgist.presentation.gists

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

internal interface GistsView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun onViewStateChanged(viewState: GistsViewState)

}
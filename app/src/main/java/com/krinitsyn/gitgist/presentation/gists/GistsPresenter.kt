package com.krinitsyn.gitgist.presentation.gists

import com.arellomobile.mvp.InjectViewState
import com.krinitsyn.core.Repository
import com.krinitsyn.gitgist.presentation.AbstractPresenter
import com.krinitsyn.utils.RxThrowable
import com.krinitsyn.utils.logger.Logger
import com.krinitsyn.utils.resource.mapResource
import com.krinitsyn.utils.schedulers.Schedulers

@InjectViewState
internal class GistsPresenter(
    private val repository: Repository,
    private val schedulers: Schedulers,
    private val logger: Logger
) : AbstractPresenter<GistsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        repository.githubGistDataService.getPublicGists()
            .observeOn(schedulers.computation)
            .mapResource(GistsFunctions::createGists)
            .map(::GistsViewState)
            .observeOn(schedulers.mainThread)
            .asAutoDispose()
            .subscribe(viewState::onViewStateChanged, RxThrowable.printStackTrace(logger, propagate = true))
    }

}
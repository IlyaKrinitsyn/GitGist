package com.krinitsyn.gitgist.presentation.gist

import com.arellomobile.mvp.InjectViewState
import com.krinitsyn.core.Repository
import com.krinitsyn.gitgist.presentation.AbstractPresenter
import com.krinitsyn.gitgist.presentation.PartialViewState
import com.krinitsyn.utils.RxThrowable
import com.krinitsyn.utils.logger.Logger
import com.krinitsyn.utils.resource.biResource
import com.krinitsyn.utils.resource.mapResource
import com.krinitsyn.utils.schedulers.Schedulers
import com.krinitsyn.utils.toConnectableFlowable
import io.reactivex.Flowable
import io.reactivex.rxkotlin.Flowables

@InjectViewState
internal class GistPresenter(
    private val repository: Repository,
    private val schedulers: Schedulers,
    private val logger: Logger,
    private val gistId: String
) : AbstractPresenter<GistView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        val rawGistFlowalbe = repository.githubGistDataService
            .getGist(gistId)
            .toConnectableFlowable()

        val rawCommitsFlowable = repository.githubGistDataService
            .getGistCommits(gistId)

        val gistInfoPartialFlowable = rawGistFlowalbe
            .observeOn(schedulers.computation)
            .mapResource(GistFunctions::createInfo)
            .map(GistPartialViewStates::info)

        val gistFilesAndCommitsPartialFlowable = Flowables
            .combineLatest(rawGistFlowalbe, rawCommitsFlowable, biResource(GistFunctions::createItems))
            .map(GistPartialViewStates::items)

        Flowable.merge(gistInfoPartialFlowable, gistFilesAndCommitsPartialFlowable)
            .scan(GistViewState(), PartialViewState.apply())
            .observeOn(schedulers.mainThread)
            .asAutoDispose()
            .subscribe(viewState::onViewStateChanged, RxThrowable.printStackTrace(logger, propagate = true))
    }

}
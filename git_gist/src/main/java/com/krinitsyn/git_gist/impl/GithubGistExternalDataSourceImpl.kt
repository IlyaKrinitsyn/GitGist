package com.krinitsyn.git_gist.impl

import com.krinitsyn.git_gist.GithubGistExternalDataSource
import com.krinitsyn.git_gist.data.Commit
import com.krinitsyn.git_gist.data.Gist
import com.krinitsyn.git_gist.data.external.GithubGistTransformers
import com.krinitsyn.utils.RxThrowable
import com.krinitsyn.utils.logger.Logger
import com.krinitsyn.utils.network.convertNetworkException
import com.krinitsyn.utils.schedulers.Schedulers
import io.reactivex.Single

internal class GithubGistExternalDataSourceImpl(
    githubGistApiFactory: () -> GithubGistApi,
    private val schedulers: Schedulers,
    private val logger: Logger
) : GithubGistExternalDataSource {

    private val githubGistApi: GithubGistApi by lazy(LazyThreadSafetyMode.SYNCHRONIZED, githubGistApiFactory)

    override fun getPublicGists(): Single<List<Gist>> = githubGistApi.getPublicGists()
        .observeOn(schedulers.computation)
        .convertNetworkException()
        .map(GithubGistTransformers::gists)
        .doOnError(RxThrowable.printStackTrace(logger, propagate = false))

    override fun getGist(gistId: String): Single<Gist> = githubGistApi.getGist(gistId)
        .observeOn(schedulers.computation)
        .convertNetworkException()
        .map(GithubGistTransformers::gist)
        .doOnError(RxThrowable.printStackTrace(logger, propagate = false))

    override fun getGistCommits(gistId: String): Single<List<Commit>> = githubGistApi.getGistCommits(gistId)
        .observeOn(schedulers.computation)
        .convertNetworkException()
        .map(GithubGistTransformers::commits)
        .doOnError(RxThrowable.printStackTrace(logger, propagate = false))

}
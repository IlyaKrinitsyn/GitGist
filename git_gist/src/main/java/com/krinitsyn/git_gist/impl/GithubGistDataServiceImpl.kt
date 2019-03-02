package com.krinitsyn.git_gist.impl

import com.krinitsyn.git_gist.GithubGistDataService
import com.krinitsyn.git_gist.GithubGistExternalDataSource
import com.krinitsyn.git_gist.data.Commit
import com.krinitsyn.git_gist.data.Gist
import com.krinitsyn.utils.logger.Logger
import com.krinitsyn.utils.resource.Resource
import com.krinitsyn.utils.resource.asResource
import com.krinitsyn.utils.schedulers.Schedulers
import io.reactivex.Flowable

internal class GithubGistDataServiceImpl(
    private val githubGistExternalDataSource: GithubGistExternalDataSource,
    private val schedulers: Schedulers,
    private val logger: Logger
) : GithubGistDataService {

    override fun getPublicGists(): Flowable<Resource<List<Gist>>> = githubGistExternalDataSource.getPublicGists()
        .observeOn(schedulers.computation)
        .asResource()

    override fun getGist(gistId: String): Flowable<Resource<Gist>> = githubGistExternalDataSource.getGist(gistId)
        .observeOn(schedulers.computation)
        .asResource()

    override fun getGistCommits(gistId: String): Flowable<Resource<List<Commit>>> = githubGistExternalDataSource.getGistCommits(gistId)
        .observeOn(schedulers.computation)
        .asResource()

}
package com.krinitsyn.git_gist

import com.krinitsyn.git_gist.impl.GithubGistApi
import com.krinitsyn.git_gist.impl.GithubGistDataServiceImpl
import com.krinitsyn.git_gist.impl.GithubGistExternalDataSourceImpl
import com.krinitsyn.utils.logger.Logger
import com.krinitsyn.utils.schedulers.Schedulers

object GithubGistProvider {

    fun provide(schedulers: Schedulers, logger: Logger): GithubGistDataService {
        val retrofit = GithubGistRetrofitProvider.provideRetrofit(schedulers.io)
        val githubGistApiFactory = { retrofit.create(GithubGistApi::class.java) }
        val githubGistExternalDataSource: GithubGistExternalDataSource = GithubGistExternalDataSourceImpl(
            githubGistApiFactory = githubGistApiFactory,
            schedulers = schedulers,
            logger = logger
        )
        return GithubGistDataServiceImpl(
            githubGistExternalDataSource = githubGistExternalDataSource,
            schedulers = schedulers,
            logger = logger
        )
    }

}
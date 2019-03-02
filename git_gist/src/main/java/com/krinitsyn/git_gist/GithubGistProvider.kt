package com.krinitsyn.git_gist

import android.content.Context
import com.krinitsyn.git_gist.impl.GithubGistApi
import com.krinitsyn.git_gist.impl.GithubGistDataServiceImpl
import com.krinitsyn.git_gist.impl.GithubGistExternalDataSourceImpl
import com.krinitsyn.utils.logger.Logger
import com.krinitsyn.utils.schedulers.Schedulers
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

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

    fun setUpPicassoImageLoader(context: Context) {
        val picasso = Picasso.Builder(context)
            .downloader(OkHttp3Downloader(context.cacheDir))
            .build()
        Picasso.setSingletonInstance(picasso)
    }

}
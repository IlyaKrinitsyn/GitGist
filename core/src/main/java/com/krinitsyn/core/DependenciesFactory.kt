package com.krinitsyn.core

import android.content.Context
import com.krinitsyn.git_gist.GithubGistProvider
import com.krinitsyn.utils.logger.EmptyLogger
import com.krinitsyn.utils.logger.LogcatLogger
import com.krinitsyn.utils.schedulers.ApplicationSchedulers
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

object DependenciesFactory {

    fun create(): Dependencies {
        val schedulers = ApplicationSchedulers()
        val logger = if (BuildConfig.DEBUG) LogcatLogger() else EmptyLogger()

        val githubGistDataService = GithubGistProvider.provide(schedulers, logger)

        val repository = Repository(githubGistDataService)

        return Dependencies(repository, schedulers, logger)
    }

    fun setUpPicassoImageLoader(context: Context) {
        val picasso = Picasso.Builder(context)
            .downloader(OkHttp3Downloader(context.cacheDir))
            .build()
        Picasso.setSingletonInstance(picasso)
    }

}
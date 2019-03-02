package com.krinitsyn.core

import com.krinitsyn.git_gist.GithubGistProvider
import com.krinitsyn.utils.logger.EmptyLogger
import com.krinitsyn.utils.logger.LogcatLogger
import com.krinitsyn.utils.schedulers.ApplicationSchedulers

object DependenciesFactory {

    fun create(): Dependencies {
        val schedulers = ApplicationSchedulers()
        val logger = if (BuildConfig.DEBUG) LogcatLogger() else EmptyLogger()

        val githubGistDataService = GithubGistProvider.provide(schedulers, logger)

        val repository = Repository(githubGistDataService)

        return Dependencies(repository, schedulers, logger)
    }

}
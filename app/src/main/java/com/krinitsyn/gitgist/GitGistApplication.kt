package com.krinitsyn.gitgist

import android.app.Application
import android.content.Context
import com.krinitsyn.core.Dependencies
import com.krinitsyn.core.DependenciesFactory

internal class GitGistApplication : Application(), DependenciesProvider {

    private val dependencies by lazy {
        DependenciesFactory.create()
    }

    override fun onCreate() {
        super.onCreate()
        DependenciesFactory.setUpPicassoImageLoader(this)
    }

    override fun dependencies(): Dependencies = dependencies

}

internal interface DependenciesProvider {

    fun dependencies(): Dependencies

}

internal fun Context.dependencies(): Dependencies =
    (this.applicationContext as? DependenciesProvider)?.dependencies()
        ?: throw IllegalStateException("Application class should implement DependenciesProvider interface")
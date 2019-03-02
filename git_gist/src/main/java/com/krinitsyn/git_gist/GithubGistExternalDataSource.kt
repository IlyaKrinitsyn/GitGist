package com.krinitsyn.git_gist

import com.krinitsyn.git_gist.data.Commit
import com.krinitsyn.git_gist.data.Gist
import io.reactivex.Single

internal interface GithubGistExternalDataSource {

    fun getPublicGists(): Single<List<Gist>>

    fun getGist(gistId: String): Single<Gist>

    fun getGistCommits(gistId: String): Single<List<Commit>>

}
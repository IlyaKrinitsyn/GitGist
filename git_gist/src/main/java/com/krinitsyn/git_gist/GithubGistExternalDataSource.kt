package com.krinitsyn.git_gist

import com.krinitsyn.git_gist.data.Gist
import io.reactivex.Single

internal interface GithubGistExternalDataSource {

    fun getPublicGists(): Single<List<Gist>>

}
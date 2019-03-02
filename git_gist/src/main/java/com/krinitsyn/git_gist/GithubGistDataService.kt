package com.krinitsyn.git_gist

import com.krinitsyn.git_gist.data.Commit
import com.krinitsyn.git_gist.data.Gist
import com.krinitsyn.utils.resource.Resource
import io.reactivex.Flowable

interface GithubGistDataService {

    fun getPublicGists(): Flowable<Resource<List<Gist>>>

    fun getGist(gistId: String): Flowable<Resource<Gist>>

    fun getGistCommits(gistId: String): Flowable<Resource<List<Commit>>>

}
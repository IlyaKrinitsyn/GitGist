package com.krinitsyn.git_gist.impl

import com.krinitsyn.git_gist.data.Gist
import com.krinitsyn.git_gist.data.external.GistExt
import io.reactivex.Single
import retrofit2.http.GET

internal interface GithubGistApi {

    @GET("gists/public")
    fun getPublicGists(): Single<List<GistExt>>

}
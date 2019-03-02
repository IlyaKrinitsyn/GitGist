package com.krinitsyn.git_gist.impl

import com.krinitsyn.git_gist.data.external.CommitExt
import com.krinitsyn.git_gist.data.external.GistExt
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

internal interface GithubGistApi {

    @GET("gists/public")
    fun getPublicGists(): Single<List<GistExt>>

    @GET("gists/{gistId}")
    fun getGist(@Path("gistId") gistId: String): Single<GistExt>

    @GET("gists/{gistId}/commits")
    fun getGistCommits(@Path("gistId") gistId: String): Single<List<CommitExt>>

}
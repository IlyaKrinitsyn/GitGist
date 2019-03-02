package com.krinitsyn.git_gist.data.external

import com.fasterxml.jackson.annotation.JsonProperty
import com.krinitsyn.git_gist.data.Gist

internal data class GistExt(
    @field:JsonProperty("url") val url: String = "",
    @field:JsonProperty("forks_url") val forksUrl: String = "",
    @field:JsonProperty("commits_url") val commitsUrl: String = "",
    @field:JsonProperty("id") val id: String = "",
    @field:JsonProperty("files") val files: Map<String, FileExt> = emptyMap(),
    @field:JsonProperty("description") val description: String? = null,
    @field:JsonProperty("comments") val comments: Int = 0,
    @field:JsonProperty("comments_url") val commentsUrl: String = "",
    @field:JsonProperty("owner") val owner: UserExt = UserExt(),
    @field:JsonProperty("truncated") val truncated: Boolean = false
) {

    internal data class FileExt(
        @field:JsonProperty("file_name") val fileName: String = "",
        @field:JsonProperty("type") val type: String = "",
        @field:JsonProperty("language") val language: String? = null,
        @field:JsonProperty("raw_url") val rawUrl: String = "",
        @field:JsonProperty("size") val size: Long = 1L
    )

}
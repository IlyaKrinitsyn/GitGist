package com.krinitsyn.git_gist.data.external

import com.fasterxml.jackson.annotation.JsonProperty

internal data class UserExt(
    @field:JsonProperty("login") val login: String = "",
    @field:JsonProperty("id") val id: Long = 1L,
    @field:JsonProperty("avatar_url") val avatarUrl: String = "",
    @field:JsonProperty("url") val url: String = "",
    @field:JsonProperty("gists_url") val gistsUrl: String = ""
)
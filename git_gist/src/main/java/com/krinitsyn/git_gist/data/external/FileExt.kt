package com.krinitsyn.git_gist.data.external

import com.fasterxml.jackson.annotation.JsonProperty

internal data class FileExt(
    @field:JsonProperty("file_name") val fileName: String = "",
    @field:JsonProperty("type") val type: String = "",
    @field:JsonProperty("language") val language: String? = null,
    @field:JsonProperty("raw_url") val rawUrl: String = "",
    @field:JsonProperty("content") val content: String? = null,
    @field:JsonProperty("size") val size: Long = 1L
)
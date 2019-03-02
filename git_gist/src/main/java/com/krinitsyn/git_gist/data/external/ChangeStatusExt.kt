package com.krinitsyn.git_gist.data.external

import com.fasterxml.jackson.annotation.JsonProperty

internal data class ChangeStatusExt(
    @field:JsonProperty("total") val total: Long = 0L,
    @field:JsonProperty("additions") val additions: Long = 0L,
    @field:JsonProperty("deletions") val deletions: Long = 0L
)
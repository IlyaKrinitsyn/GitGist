package com.krinitsyn.git_gist.data.external

import com.fasterxml.jackson.annotation.JsonProperty

internal data class CommitExt(
    @field:JsonProperty("user") val user: UserExt = UserExt(),
    @field:JsonProperty("version") val version: String = "",
    @field:JsonProperty("committed_at") val committedAt: String = "",
    @field:JsonProperty("change_status") val changeStatus: ChangeStatusExt = ChangeStatusExt(),
    @field:JsonProperty("url") val url: String = ""
)
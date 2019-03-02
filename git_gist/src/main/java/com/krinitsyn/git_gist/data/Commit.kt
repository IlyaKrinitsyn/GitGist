package com.krinitsyn.git_gist.data

data class Commit(
    val user: User,
    val version: String,
    val committedAt: String,
    val changeStatus: ChangeStatus,
    val url: String
)
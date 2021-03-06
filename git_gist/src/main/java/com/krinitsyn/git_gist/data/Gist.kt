package com.krinitsyn.git_gist.data

data class Gist(
    val url: String,
    val forksUrl: String,
    val commitsUrl: String,
    val id: String,
    val files: Map<String, File>,
    val description: String?,
    val comments: Int,
    val commentsUrl: String,
    val owner: User,
    val truncated: Boolean
)
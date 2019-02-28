package com.krinitsyn.git_gist.data

data class User(
    val login: String,
    val id: Long,
    val avatarUrl: String,
    val url: String,
    val gistsUrl: String
)
package com.krinitsyn.git_gist.data

data class File(
    val fileName: String,
    val type: String,
    val language: String?,
    val rawUrl: String,
    val size: Long
)
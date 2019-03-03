package com.krinitsyn.git_gist.data

/**
 * Check this bullshit
 * @link <a href="https://api.github.com/gists/023e17436121a0ebc018e5ec85a68ada/commits">User is null</a>
 */
data class Commit(
    val user: User?,
    val version: String,
    val committedAt: String,
    val changeStatus: ChangeStatus,
    val url: String
)
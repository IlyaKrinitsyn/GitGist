package com.krinitsyn.utils.network

data class ConnectionException(
    val errorMessage: String = "No connection",
    val throwable: Throwable = Throwable(errorMessage)
) : RuntimeException(errorMessage, throwable)
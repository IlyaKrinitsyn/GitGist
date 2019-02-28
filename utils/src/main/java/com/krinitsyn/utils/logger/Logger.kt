package com.krinitsyn.utils.logger

interface Logger {

    fun d(tag: String, message: String, throwable: Throwable? = null)

    fun w(tag: String, message: String, throwable: Throwable? = null)

    fun e(tag: String, message: String, throwable: Throwable? = null)

}
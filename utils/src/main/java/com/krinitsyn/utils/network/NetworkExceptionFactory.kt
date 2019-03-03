package com.krinitsyn.utils.network

import com.krinitsyn.utils.Predicate
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal object NetworkExceptionFactory {

    fun <T> convert(upstream: Single<T>): Single<T> = upstream.onErrorResumeNext { throwable ->
        when {
            isNetworkException(throwable) -> Single
                .error(ConnectionException(throwable = throwable))
            else -> Single.error(throwable)
        }
    }

    fun <T> convert(upstream: Maybe<T>): Maybe<T> = upstream.onErrorResumeNext { throwable: Throwable ->
        when {
            isNetworkException(throwable) -> Maybe
                .error(ConnectionException(throwable = throwable))
            else -> Maybe.error(throwable)
        }
    }

    fun <T> convert(upstream: Flowable<T>): Flowable<T> =
        upstream.onErrorResumeNext { throwable: Throwable ->
            when {
                isNetworkException(throwable) -> Flowable
                    .error(ConnectionException(throwable = throwable))
                else -> Flowable.error(throwable)
            }
        }

    private val isNetworkException: Predicate<Throwable> = { throwable ->
        throwable is SocketTimeoutException || throwable is UnknownHostException || throwable is SocketException
    }

}
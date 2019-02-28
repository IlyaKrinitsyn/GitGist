package com.krinitsyn.utils.resource

import androidx.annotation.IntRange

sealed class Resource<out T : Any> {

    abstract val timestamp: Long

    data class Loading(
            @IntRange(from = 0L) override val timestamp: Long = System.currentTimeMillis()
    ) : Resource<Nothing>()

    data class Data<out T : Any>(
            val data: T,
            @IntRange(from = 0L) override val timestamp: Long = System.currentTimeMillis()
    ) : Resource<T>()

    data class Error(
            val throwable: Throwable,
            @IntRange(from = 0L) override val timestamp: Long = System.currentTimeMillis()
    ) : Resource<Nothing>()

}

inline fun <T : Any, R : Any> Resource<T>.map(
        crossinline mapper: (T) -> R
): Resource<R> = when (this) {
    is Resource.Loading -> this
    is Resource.Data<T> -> try {
        Resource.Data(data = mapper(this.data))
    } catch (throwable: Throwable) {
        Resource.Error(throwable = throwable)
    }
    is Resource.Error -> this
}
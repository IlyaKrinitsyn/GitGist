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

    infix operator fun plus(other: Resource<*>): Resource<*> = when {
        (this is Error || other is Error) -> Error((this as? Error)?.throwable
            ?: (other as? Error)?.throwable
            ?: throw IllegalStateException("One of resources should be in Error state"))
        (this is Data && other is Data) -> Data(Any::class)
        else -> Loading()
    }

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

fun <T1 : Any, T2 : Any, R : Any> biResource(
    combiner: (T1, T2) -> R
): (Resource<T1>, Resource<T2>) -> Resource<R> = { resource1, resource2 ->
    val combinedResource = resource1 + resource2
    when (combinedResource) {
        is Resource.Loading -> Resource.Loading()
        is Resource.Data -> {
            val data1 = (resource1 as? Resource.Data)?.data
                ?: throw IllegalArgumentException("Resource should be in success state")
            val data2 = (resource2 as? Resource.Data)?.data
                ?: throw IllegalArgumentException("Resource should be in success state")
            Resource.Data(combiner(data1, data2))
        }
        is Resource.Error -> {
            val errorResource = listOf(resource1, resource2)
                .filterIsInstance(Resource.Error::class.java)
                .sortedBy { resource -> resource.timestamp }
                .first()
            val throwable = (errorResource as? Resource.Error)?.throwable
                ?: throw IllegalArgumentException("Resource should be in error state")
            Resource.Error(throwable, errorResource.timestamp)
        }
    }
}
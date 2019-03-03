package com.krinitsyn.utils.optional

sealed class Optional<out T : Any> {

    abstract fun toNullable(): T?

}

data class Some<T : Any>(val value: T) : Optional<T>() {

    override fun toNullable(): T? = value

}

object None : Optional<Nothing>() {

    override fun toNullable(): Nothing? = null

}

fun <T : Any> T?.toOptional(): Optional<T> = if (this != null) Some(this) else None

inline fun <T : Any, R : Any> Optional<T>.map(crossinline mapper: (T) -> R?): Optional<R> = when (this) {
    is Some -> mapper(this.value).toOptional()
    is None -> None
}

inline fun <T : Any> Optional<T>.ifPresentOrElse(
    crossinline consumer: (T) -> Unit,
    crossinline action: () -> Unit = { Unit }
) = when (this) {
    is Some -> consumer(this.value)
    is None -> action()
}
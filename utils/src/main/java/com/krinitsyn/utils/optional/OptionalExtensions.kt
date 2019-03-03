package com.krinitsyn.utils.optional

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.annotations.BackpressureKind
import io.reactivex.annotations.BackpressureSupport
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.annotations.SchedulerSupport

@CheckReturnValue
@BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <T : Any, R : Any> Flowable<Optional<T>>.mapOptional(
    crossinline mapper: (T) -> R
): Flowable<Optional<R>> = switchMapSingle { optional ->
    Single.just(optional).map { plsDontShadowOptional -> plsDontShadowOptional.map(mapper) }
}
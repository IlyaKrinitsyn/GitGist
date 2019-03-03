package com.krinitsyn.utils.network

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.annotations.BackpressureKind
import io.reactivex.annotations.BackpressureSupport
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.annotations.SchedulerSupport

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T> Single<T>.convertNetworkException(): Single<T> =
    compose(NetworkExceptionFactory::convert)

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T> Maybe<T>.convertNetworkException(): Maybe<T> =
    compose(NetworkExceptionFactory::convert)

@CheckReturnValue
@BackpressureSupport(BackpressureKind.PASS_THROUGH)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T> Flowable<T>.convertNetworkException(): Flowable<T> =
    compose(NetworkExceptionFactory::convert)


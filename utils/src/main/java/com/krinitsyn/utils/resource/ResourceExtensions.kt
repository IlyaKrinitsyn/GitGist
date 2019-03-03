package com.krinitsyn.utils.resource

import com.krinitsyn.utils.optional.None
import com.krinitsyn.utils.optional.Optional
import com.krinitsyn.utils.optional.Some
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.annotations.BackpressureKind
import io.reactivex.annotations.BackpressureSupport
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.annotations.SchedulerSupport

@CheckReturnValue
@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Single<T>.asResource(): Flowable<Resource<T>> = map { data -> Resource.Data(data = data) as Resource<T> }
    .onErrorReturn { throwable -> Resource.Error(throwable = throwable) }
    .toFlowable()
    .startWith(Resource.Loading())

@CheckReturnValue
@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Maybe<T>.asResource(): Flowable<Resource<T>> = toSingle().asResource()

@CheckReturnValue
@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Flowable<T>.asResource(): Flowable<Resource<T>> =
    map { data -> Resource.Data(data = data) as Resource<T> }
        .onErrorReturn { throwable -> Resource.Error(throwable = throwable) }
        .startWith(Resource.Loading())

@CheckReturnValue
@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Flowable<Resource<T>>.resourceAsOptional(): Flowable<Optional<T>> = this.switchMap { resource ->
    when (resource) {
        is Resource.Loading, is Resource.Error -> Flowable.just(None)
        is Resource.Data -> Flowable.just(Some(resource.data))
    }
}

@CheckReturnValue
@BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any, R : Any> Flowable<Resource<T>>.mapResource(
    mapper: (T) -> R
): Flowable<Resource<R>> = switchMapSingle { resource ->
    Single.just(resource)
        .map { data -> data.map(mapper) }
}.distinctResourceUntilChanged()

@CheckReturnValue
@BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any, R : Any> Flowable<Resource<T>>.flatMapResource(
    mapper: (T) -> Flowable<Resource<R>>
): Flowable<Resource<R>> = switchMap { resource ->
    when (resource) {
        is Resource.Loading -> Flowable.just(Resource.Loading(resource.timestamp))
        is Resource.Data -> try {
            mapper(resource.data)
        } catch (throwable: Throwable) {
            Flowable.just(Resource.Error(throwable))
        }
        is Resource.Error -> Flowable.just(Resource.Error(resource.throwable, resource.timestamp))
    }
}.distinctResourceUntilChanged()

@CheckReturnValue
@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Flowable<Resource<T>>.unwrapResource(
    propagateError: Boolean = false
): Flowable<T> = this.switchMap { resource ->
    when (resource) {
        is Resource.Loading -> Flowable.empty<T>()
        is Resource.Data -> Flowable.just(resource.data)
        is Resource.Error -> when (propagateError) {
            true -> Flowable.error<T>(resource.throwable)
            false -> Flowable.empty<T>()
        }
    }
}

@CheckReturnValue
@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <T : Any> Flowable<Resource<T>>.unwrapResourceError(
    crossinline predicate: (Throwable) -> Boolean = { true }
): Flowable<Throwable> = switchMap { resource ->
    when (resource) {
        is Resource.Loading -> Flowable.empty<Throwable>()
        is Resource.Data -> Flowable.empty<Throwable>()
        is Resource.Error -> when (predicate(resource.throwable)) {
            true -> Flowable.just(resource.throwable)
            false -> Flowable.empty<Throwable>()
        }
    }
}

@CheckReturnValue
@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Flowable<Resource<T>>.distinctResourceUntilChanged(): Flowable<Resource<T>> =
    distinctUntilChanged { lhs, rhs -> lhs.javaClass == rhs.javaClass && lhs.timestamp == rhs.timestamp }

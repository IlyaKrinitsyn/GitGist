package com.krinitsyn.gitgist.presentation

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.uber.autodispose.AutoDispose
import io.reactivex.*
import io.reactivex.annotations.BackpressureKind
import io.reactivex.annotations.BackpressureSupport
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.annotations.SchedulerSupport

internal abstract class AbstractPresenter<View : MvpView> : MvpPresenter<View>() {

    private val lifecycleScopeProvider: PresenterLifecycleScopeProvider = PresenterLifecycleScopeProvider()

    init {
        lifecycleScopeProvider.onCreate()
    }

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T> Single<T>.asAutoDispose() = `as`(AutoDispose.autoDisposable(lifecycleScopeProvider))!!

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T> Observable<T>.asAutoDispose() = `as`(AutoDispose.autoDisposable(lifecycleScopeProvider))!!

    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T> Completable.asAutoDispose() = `as`(AutoDispose.autoDisposable<T>(lifecycleScopeProvider))!!

    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T> Maybe<T>.asAutoDispose() = `as`(AutoDispose.autoDisposable(lifecycleScopeProvider))!!

    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T> Flowable<T>.asAutoDispose() = `as`(AutoDispose.autoDisposable(lifecycleScopeProvider))!!

    override fun onDestroy() {
        lifecycleScopeProvider.onDestroy()
        super.onDestroy()
    }

}
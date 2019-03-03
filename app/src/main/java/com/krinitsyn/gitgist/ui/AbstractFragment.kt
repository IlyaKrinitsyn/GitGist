package com.krinitsyn.gitgist.ui

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.MvpAppCompatFragment
import com.krinitsyn.gitgist.R
import com.krinitsyn.utils.network.ConnectionException
import com.uber.autodispose.AutoDispose
import io.reactivex.*
import io.reactivex.annotations.BackpressureKind
import io.reactivex.annotations.BackpressureSupport
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.annotations.SchedulerSupport

internal abstract class AbstractFragment : MvpAppCompatFragment() {

    private val lifecycleScopeProvider = FragmentLifecycleScopeProvider()

    init {
        lifecycleScopeProvider.onAttach()
    }

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T> Single<T>.asAutoDispose() =
        `as`(AutoDispose.autoDisposable(lifecycleScopeProvider))!!

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T> Observable<T>.asAutoDispose() =
        `as`(AutoDispose.autoDisposable(lifecycleScopeProvider))!!

    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T> Completable.asAutoDispose() =
        `as`(AutoDispose.autoDisposable<T>(lifecycleScopeProvider))!!

    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T> Maybe<T>.asAutoDispose() =
        `as`(AutoDispose.autoDisposable(lifecycleScopeProvider))!!

    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T> Flowable<T>.asAutoDispose() =
        `as`(AutoDispose.autoDisposable(lifecycleScopeProvider))!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScopeProvider.onCreate()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScopeProvider.onCreateView()
    }

    override fun onStart() {
        super.onStart()
        lifecycleScopeProvider.onStart()
    }

    override fun onResume() {
        super.onResume()
        lifecycleScopeProvider.onResume()
    }

    override fun onPause() {
        lifecycleScopeProvider.onPause()
        super.onPause()
    }

    override fun onStop() {
        lifecycleScopeProvider.onStop()
        super.onStop()
    }

    override fun onDestroyView() {
        lifecycleScopeProvider.onDestroyView()
        super.onDestroyView()
    }

    override fun onDestroy() {
        lifecycleScopeProvider.onDestroy()
        super.onDestroy()
    }

    override fun onDetach() {
        lifecycleScopeProvider.onDetach()
        super.onDetach()
    }

    fun Throwable.toErrorMessage(): CharSequence {
        val context = requireContext()
        return when (this) {
            is ConnectionException -> context.getString(R.string.connection_error_message)
            else -> context.getString(R.string.unknown_error_message)
        }
    }

}
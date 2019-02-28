package com.krinitsyn.gitgist.presentation

import com.uber.autodispose.LifecycleEndedException
import com.uber.autodispose.LifecycleScopeProvider
import io.reactivex.Observable
import io.reactivex.functions.Function
import io.reactivex.subjects.BehaviorSubject

internal class PresenterLifecycleScopeProvider : LifecycleScopeProvider<PresenterLifecycleScopeProvider.PresenterEvent> {

    private val lifecycleEvents = BehaviorSubject.create<PresenterEvent>()

    enum class PresenterEvent {

        Create,
        Destroy

    }

    fun onCreate() = lifecycleEvents.onNext(PresenterEvent.Create)

    fun onDestroy() = lifecycleEvents.onNext(PresenterEvent.Destroy)

    override fun lifecycle(): Observable<PresenterEvent> = lifecycleEvents.hide()

    override fun correspondingEvents(): Function<PresenterEvent, PresenterEvent> = CorrespondingEvents

    override fun peekLifecycle(): PresenterEvent? = lifecycleEvents.value

    companion object {

        private val CorrespondingEvents = Function<PresenterEvent, PresenterEvent> {
            when (it) {
                PresenterEvent.Create -> PresenterEvent.Destroy
                else -> throw LifecycleEndedException("Cannot bind to View model lifecycle after destroy.")
            }
        }

    }

}
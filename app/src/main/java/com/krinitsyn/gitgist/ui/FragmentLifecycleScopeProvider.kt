package com.krinitsyn.gitgist.ui

import com.uber.autodispose.LifecycleEndedException
import com.uber.autodispose.LifecycleScopeProvider
import io.reactivex.Observable
import io.reactivex.functions.Function
import io.reactivex.subjects.BehaviorSubject

internal class FragmentLifecycleScopeProvider : LifecycleScopeProvider<FragmentLifecycleScopeProvider.FragmentEvent> {

    private val lifecycleEvents = BehaviorSubject.create<FragmentEvent>()

    enum class FragmentEvent {

        Attach,
        Create,
        CreateView,
        Start,
        Resume,
        Pause,
        Stop,
        DestroyView,
        Destroy,
        Detach

    }

    fun onAttach() = lifecycleEvents.onNext(FragmentEvent.Attach)

    fun onCreate() = lifecycleEvents.onNext(FragmentEvent.Create)

    fun onCreateView() = lifecycleEvents.onNext(FragmentEvent.CreateView)

    fun onStart() = lifecycleEvents.onNext(FragmentEvent.Start)

    fun onResume() = lifecycleEvents.onNext(FragmentEvent.Resume)

    fun onPause() = lifecycleEvents.onNext(FragmentEvent.Pause)

    fun onStop() = lifecycleEvents.onNext(FragmentEvent.Stop)

    fun onDestroyView() = lifecycleEvents.onNext(FragmentEvent.DestroyView)

    fun onDestroy() = lifecycleEvents.onNext(FragmentEvent.Destroy)

    fun onDetach() = lifecycleEvents.onNext(FragmentEvent.Detach)

    override fun lifecycle(): Observable<FragmentEvent> = lifecycleEvents.hide()

    override fun correspondingEvents(): Function<FragmentEvent, FragmentEvent> = CorrespondingEvents

    override fun peekLifecycle(): FragmentEvent? = lifecycleEvents.value

    companion object {

        private val CorrespondingEvents = Function<FragmentEvent, FragmentEvent> {
            when (it) {
                FragmentEvent.Attach -> FragmentEvent.Detach
                FragmentEvent.Create -> FragmentEvent.Destroy
                FragmentEvent.CreateView -> FragmentEvent.DestroyView
                FragmentEvent.Start -> FragmentEvent.Stop
                FragmentEvent.Resume -> FragmentEvent.Pause
                FragmentEvent.Pause -> FragmentEvent.Stop
                FragmentEvent.Stop -> FragmentEvent.DestroyView
                FragmentEvent.DestroyView -> FragmentEvent.Destroy
                FragmentEvent.Destroy -> FragmentEvent.Detach
                else -> throw LifecycleEndedException(
                        "Cannot bind to Fragment lifecycle after detach.")
            }
        }

    }

}
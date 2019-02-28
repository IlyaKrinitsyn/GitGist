package com.krinitsyn.utils.schedulers

import io.reactivex.Scheduler

interface Schedulers {

    val io: Scheduler

    val computation: Scheduler

    val mainThread: Scheduler

}
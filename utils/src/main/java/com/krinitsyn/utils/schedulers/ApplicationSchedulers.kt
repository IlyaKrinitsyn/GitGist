package com.krinitsyn.utils.schedulers

typealias RxSchedulers = io.reactivex.schedulers.Schedulers
typealias RxAndroidSchedulers = io.reactivex.android.schedulers.AndroidSchedulers

class ApplicationSchedulers() : Schedulers {

    override val io = RxSchedulers.io()

    override val computation = RxSchedulers.computation()

    override val mainThread = RxAndroidSchedulers.mainThread()!!

}
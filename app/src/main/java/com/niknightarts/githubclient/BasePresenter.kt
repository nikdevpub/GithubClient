package com.niknightarts.githubclient

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter
import moxy.MvpView

abstract class BasePresenter<V : MvpView> : MvpPresenter<V>() {

    private val disposables = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    protected fun clearDisposables() {
        disposables.clear()
    }

    override fun onDestroy() {
        clearDisposables()
        super.onDestroy()
    }
}

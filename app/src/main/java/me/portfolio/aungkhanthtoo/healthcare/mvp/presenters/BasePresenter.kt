package me.portfolio.aungkhanthtoo.healthcare.mvp.presenters

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.annotation.CallSuper
import me.portfolio.aungkhanthtoo.healthcare.mvp.views.BaseView
import me.portfolio.aungkhanthtoo.healthcare.utils.Error

abstract class BasePresenter<V: BaseView<D>, D: Any> : ViewModel(){

    protected lateinit var view: V

    val  dataLiveData by lazy { MutableLiveData<D>() }

    val errorLiveData by lazy { MutableLiveData<Error>() }

    protected lateinit var application: Application

    abstract fun onUiReady(args: Bundle? = null): Boolean

    @CallSuper
    open fun initView(view: V, app: Application) {
        this.view = view
        application = app
    }

}
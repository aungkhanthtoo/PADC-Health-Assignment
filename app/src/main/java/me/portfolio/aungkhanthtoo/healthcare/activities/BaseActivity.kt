package me.portfolio.aungkhanthtoo.healthcare.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import me.portfolio.aungkhanthtoo.healthcare.mvp.presenters.BasePresenter
import me.portfolio.aungkhanthtoo.healthcare.mvp.views.BaseView
import me.portfolio.aungkhanthtoo.healthcare.utils.observe

abstract class BaseActivity<P: BasePresenter<V, D>, V: BaseView<D>, D: Any>:
        AppCompatActivity(),
        BaseView<D> {

    protected lateinit var presenter: P
    private set

    protected abstract val thisView: V

    protected abstract fun onCreatePresenter() : P

    protected abstract fun setUpUIComponents() : Boolean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = onCreatePresenter()
        presenter.initView(thisView, application)

        presenter.let {
            observe(it.dataLiveData, ::showData)
            observe(it.errorLiveData, ::showError)
        }
    }

}
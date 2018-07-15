package me.portfolio.aungkhanthtoo.healthcare.mvp.views

import me.portfolio.aungkhanthtoo.healthcare.utils.Error

interface BaseView<D: Any> {

    fun showData(data: D?)

    fun showError(error: Error?)

}
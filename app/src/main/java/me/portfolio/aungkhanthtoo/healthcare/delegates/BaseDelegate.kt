package me.portfolio.aungkhanthtoo.healthcare.delegates

import android.view.View

interface BaseDelegate<T> {

    fun onItemClick(position: Int, item: T)
}
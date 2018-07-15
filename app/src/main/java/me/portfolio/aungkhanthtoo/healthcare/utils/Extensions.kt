package me.portfolio.aungkhanthtoo.healthcare.utils

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import me.portfolio.aungkhanthtoo.healthcare.viewholders.BaseViewHolder
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Extensions Functions for This App.
 */

fun ViewGroup.inflate(layoutId: Int) = LayoutInflater.from(this.context).inflate(layoutId, this, false)

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(): T {
    return ViewModelProviders.of(this)[T::class.java]
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}

fun <T, D, VH : BaseViewHolder<T, D>> RecyclerView.setUpLinear(adapter: ListAdapter<T, VH>) {
    hasFixedSize()
    layoutManager = LinearLayoutManager(this.context)
    itemAnimator = DefaultItemAnimator()
    addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
}

fun ImageView.load(url: String){
    Glide.with(context)
            .load(url)
            .into(this)
}

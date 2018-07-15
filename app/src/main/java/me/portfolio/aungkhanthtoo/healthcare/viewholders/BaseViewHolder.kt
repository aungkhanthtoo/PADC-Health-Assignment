package me.portfolio.aungkhanthtoo.healthcare.viewholders

import android.content.Context
import android.support.annotation.CallSuper
import android.support.v7.widget.RecyclerView
import android.view.View
import me.portfolio.aungkhanthtoo.healthcare.delegates.BaseDelegate

@Suppress("LeakingThis")
abstract class BaseViewHolder<T : Any, D : BaseDelegate<T>>(itemView: View, private val delegate: D) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

    protected val context: Context = itemView.context
    private lateinit var current: T

    init {
        itemView.setOnClickListener(this)
    }

    @CallSuper
    open fun bindData(item: T) {
        current = item
    }

    override fun onClick(v: View?) {
        delegate.onItemClick(layoutPosition, current)
    }

}
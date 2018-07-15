package me.portfolio.aungkhanthtoo.healthcare.components

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View

/**
 * Created by aung on 11/25/17.
 */
class SmartRecyclerView : RecyclerView {
    private var mEmptyView: View? = null
    private val dataObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
            checkIfEmpty()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            checkIfEmpty()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            checkIfEmpty()
        }
    }

    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {}

    override fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
        val oldAdapter = getAdapter()
        oldAdapter?.unregisterAdapterDataObserver(dataObserver)
        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(dataObserver)
        checkIfEmpty()
    }

    fun setEmptyView(emptyView: View) {
        mEmptyView = emptyView
    }

    /**
     * check if adapter connected to SRV is empty. If so, show emptyView.
     */
    private fun checkIfEmpty() {
        val isEmpty = adapter.itemCount == 0
        val emptyView = mEmptyView
        if (emptyView != null) {
            emptyView.visibility = if (isEmpty) View.VISIBLE else View.INVISIBLE
            visibility = if (isEmpty) View.INVISIBLE else View.VISIBLE
        }
    }
}

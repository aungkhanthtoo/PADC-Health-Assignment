package me.portfolio.aungkhanthtoo.healthcare.components

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by aung on 11/26/17.
 */
class SmartScrollListener(private val mSmartScrollListener: OnSmartScrollListener) : RecyclerView.OnScrollListener() {
    private var isListEndReached = false

    interface OnSmartScrollListener {
        fun onLoadMore()
    }

    override fun onScrolled(rv: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(rv, dx, dy)

        val layoutManager = rv!!.layoutManager as LinearLayoutManager
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
        if (5 + pastVisibleItems >= totalItemCount) {
            //isListEndReached = false
            mSmartScrollListener.onLoadMore()
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView?, scrollState: Int) {
        super.onScrollStateChanged(recyclerView, scrollState)

//        if (/*scrollState == RecyclerView.SCROLL_STATE_IDLE  && */
//               layoutManager.findLastCompletelyVisibleItemPosition() + 5 >= layoutManager.itemCount
//               /* && !isListEndReached */) {
//            isListEndReached = true
//            mSmartScrollListener.onLoadMore()
//        }
    }
}


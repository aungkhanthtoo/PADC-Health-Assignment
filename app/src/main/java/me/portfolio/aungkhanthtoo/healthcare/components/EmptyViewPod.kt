package me.portfolio.aungkhanthtoo.healthcare.components

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.view_pod_empty.view.*


/**
 * Created by aung on 11/25/17.
 */

class EmptyViewPod : RelativeLayout {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setEmptyData(emptyImageId: Int, emptyMsg: String) {
        iv_empty!!.setImageResource(emptyImageId)
        tv_empty!!.text = emptyMsg
    }

    fun setEmptyData(emptyMsg: String) {
        tv_empty!!.text = emptyMsg
    }

}

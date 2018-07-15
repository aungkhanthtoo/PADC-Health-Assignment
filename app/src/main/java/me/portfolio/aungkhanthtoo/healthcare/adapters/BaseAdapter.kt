package me.portfolio.aungkhanthtoo.healthcare.adapters

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import me.portfolio.aungkhanthtoo.healthcare.delegates.BaseDelegate
import me.portfolio.aungkhanthtoo.healthcare.viewholders.BaseViewHolder

abstract class BaseAdapter<VH: BaseViewHolder<T, D>, T: Any, D: BaseDelegate<T>> (
        diffCallback: DiffUtil.ItemCallback<T>,
        protected val delegate: D) : ListAdapter<T, VH>(diffCallback){

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (holder.adapterPosition != RecyclerView.NO_POSITION){
            holder.bindData(getItem(holder.adapterPosition))
        }
    }

}

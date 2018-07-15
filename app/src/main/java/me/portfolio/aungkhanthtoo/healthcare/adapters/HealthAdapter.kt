package me.portfolio.aungkhanthtoo.healthcare.adapters

import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import me.portfolio.aungkhanthtoo.healthcare.delegates.HealthItemDelegate
import me.portfolio.aungkhanthtoo.healthcare.R
import me.portfolio.aungkhanthtoo.healthcare.data.HealthInfo
import me.portfolio.aungkhanthtoo.healthcare.data.vos.HealthcareInfo
import me.portfolio.aungkhanthtoo.healthcare.utils.inflate
import me.portfolio.aungkhanthtoo.healthcare.viewholders.HealthInfoViewHolder

val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HealthcareInfo>(){
    override fun areItemsTheSame(oldItem: HealthcareInfo, newItem: HealthcareInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HealthcareInfo, newItem: HealthcareInfo): Boolean {
        return oldItem == newItem
    }
}

class HealthAdapter(delegate: HealthItemDelegate) :
        BaseAdapter<HealthInfoViewHolder, HealthcareInfo, HealthItemDelegate>(DIFF_CALLBACK, delegate){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthInfoViewHolder {
       return HealthInfoViewHolder(parent.inflate(R.layout.health_item_layout), delegate)
    }

}
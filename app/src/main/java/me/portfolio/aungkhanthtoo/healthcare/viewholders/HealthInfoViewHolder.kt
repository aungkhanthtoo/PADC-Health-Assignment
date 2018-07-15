package me.portfolio.aungkhanthtoo.healthcare.viewholders

import android.view.View
import kotlinx.android.synthetic.main.health_item_layout.view.*
import me.portfolio.aungkhanthtoo.healthcare.delegates.HealthItemDelegate
import me.portfolio.aungkhanthtoo.healthcare.data.vos.HealthcareInfo
import me.portfolio.aungkhanthtoo.healthcare.utils.load

class HealthInfoViewHolder(itemView: View, delegate: HealthItemDelegate):
        BaseViewHolder<HealthcareInfo, HealthItemDelegate>(itemView, delegate) {

    private val tvTitle = itemView.tvHealthItemTitle
    private val ivImage = itemView.ivHealthItem
    private val tvDate = itemView.tvHealthItemTime
    private val tvAuthor = itemView.tvHealthItemAuthor
    private val tvDesc = itemView.tvHealthItemDesc

    override fun bindData(item: HealthcareInfo) {
        super.bindData(item)
        // bind data here
        tvTitle.text = item.title
        tvDate.text = item.publishedDate
        tvAuthor.text = item.author?.authorName
        tvDesc.text = item.shortDescription
        ivImage.load(item.image)
    }

}
package me.portfolio.aungkhanthtoo.healthcare.mvp.presenters

import android.os.Bundle
import me.portfolio.aungkhanthtoo.healthcare.data.model.HealthInfoModel
import me.portfolio.aungkhanthtoo.healthcare.data.vos.HealthcareInfo
import me.portfolio.aungkhanthtoo.healthcare.delegates.HealthItemDelegate
import me.portfolio.aungkhanthtoo.healthcare.mvp.views.HealthInfoView

class HealthInfoPresenter:
        BasePresenter<HealthInfoView, List<HealthcareInfo>>(), HealthItemDelegate {

    override fun onItemClick(position: Int, item: HealthcareInfo) {
        view.showInfoDetailScreen(item.completeUrl)
    }

    override fun onUiReady(args: Bundle?): Boolean {
        view.showLoading()
        HealthInfoModel.getInstance(application)
                .loadHealthInfos(dataLiveData, errorLiveData)

        return true
    }

}
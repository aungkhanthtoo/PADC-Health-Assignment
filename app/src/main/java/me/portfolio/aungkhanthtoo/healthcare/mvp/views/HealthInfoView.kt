package me.portfolio.aungkhanthtoo.healthcare.mvp.views

import me.portfolio.aungkhanthtoo.healthcare.data.HealthInfo
import me.portfolio.aungkhanthtoo.healthcare.data.vos.HealthcareInfo

interface HealthInfoView: BaseView<List<HealthcareInfo>> {

    fun showLoading()

    fun hideLoading()

    fun showInfoDetailScreen(url: String)

}
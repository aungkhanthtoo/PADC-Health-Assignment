package me.portfolio.aungkhanthtoo.healthcare.activities

import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.design.widget.Snackbar
import android.support.transition.Explode
import android.support.transition.Fade
import android.support.transition.TransitionManager
import android.support.transition.TransitionSet
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import me.portfolio.aungkhanthtoo.healthcare.R
import me.portfolio.aungkhanthtoo.healthcare.adapters.HealthAdapter
import me.portfolio.aungkhanthtoo.healthcare.data.vos.HealthcareInfo
import me.portfolio.aungkhanthtoo.healthcare.mvp.presenters.HealthInfoPresenter
import me.portfolio.aungkhanthtoo.healthcare.mvp.views.HealthInfoView
import me.portfolio.aungkhanthtoo.healthcare.utils.*
import android.widget.FrameLayout
import android.support.v4.view.animation.FastOutLinearInInterpolator
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.TranslateAnimation


class MainActivity : BaseActivity<HealthInfoPresenter, HealthInfoView, List<HealthcareInfo>>(),
        HealthInfoView {

    private val adapter by lazy { HealthAdapter(presenter) }
    override val thisView: HealthInfoView = this

    override fun onCreatePresenter(): HealthInfoPresenter = getViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setUpUIComponents()
    }

    override fun setUpUIComponents(): Boolean {
        rvHealth.setUpLinear(adapter)
        rvHealth.adapter = adapter
        return presenter.onUiReady()
    }

    override fun showData(data: List<HealthcareInfo>?) {
        data?.let {
            hideLoading()
            adapter.submitList(it)
        }
    }

    private fun showLayoutAnimation() {
        Log.d("LayoutAnimation", "count: ${rvHealth.layoutManager.itemCount}")
        rvHealth.scheduleLayoutAnimation()
    }

    override fun showInfoDetailScreen(url: String) {
        showChromeTab(url)
    }

    override fun showError(error: Error?) {
        error?.let {
            when (it) {
                is EmptyError -> {
                    showEmptyError(error as EmptyError)
                }
                is NetworkError -> {
                    showNetworkError(error as NetworkError)
                }
                is ServerError -> {

                }
            }
        }
    }

    private fun showEmptyError(emptyError: EmptyError) {
        rvHealth.setEmptyView(emptyView)
    }

    override fun showLoading() {
        if (animation_view.visibility != View.VISIBLE)
            animation_view.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        val set = TransitionSet()
                .addTransition(Explode())
                .addTransition(Fade())
                .setDuration(1000)
                .setInterpolator(DecelerateInterpolator())

        TransitionManager.beginDelayedTransition(rootLayout, set)
        if (animation_view.visibility != View.GONE)
            animation_view.visibility = View.GONE
    }

    private fun showChromeTab(url: String) {
        val packageName = "com.android.chrome"
        val builder = CustomTabsIntent.Builder().apply {
            setToolbarColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary)) // Color.parseColor("#d52125")
            setShowTitle(true)
            setStartAnimations(applicationContext, R.anim.item_anim_fall_in, android.R.anim.fade_in)
        }
        val customTabsIntent = builder.build()
        customTabsIntent.intent.setPackage(packageName)
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }

    private fun showNetworkError(error: NetworkError) {
        val snackbar = Snackbar.make(rootLayout, error.msg, Snackbar.LENGTH_INDEFINITE)
                .setActionTextColor(ContextCompat.getColor(applicationContext, R.color.colorAccent))
                .setAction("RETRY") {
                    presenter.onUiReady()
                }
        val snackBarView = snackbar.view as FrameLayout
        val params = snackBarView.getChildAt(0).layoutParams as FrameLayout.LayoutParams
        params.setMargins(params.leftMargin, params.topMargin, params.rightMargin, params.bottomMargin + 32)
        snackBarView.getChildAt(0).layoutParams = params
        snackbar.show()
    }

}

package com.niknightarts.githubclient.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.niknightarts.githubclient.App
import com.niknightarts.githubclient.BaseActivity
import com.niknightarts.githubclient.R
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    companion object {
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override val layoutId = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        App.initMainComponent()?.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(Navigator())
    }

    override fun onDestroy() {
        super.onDestroy()

        if (isFinishing) {
            App.destroyMainComponent()
        }
    }

    override fun onBackPressed() {
        super.handleOnBackPressed(R.id.main_container)
    }

    private inner class Navigator : SupportAppNavigator(this@MainActivity, R.id.main_container)
}

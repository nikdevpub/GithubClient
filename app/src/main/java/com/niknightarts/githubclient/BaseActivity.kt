package com.niknightarts.githubclient

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.niknightarts.githubclient.utils.BackButtonListener
import moxy.MvpAppCompatActivity

abstract class BaseActivity : MvpAppCompatActivity() {

    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
    }

    protected fun handleOnBackPressed(@IdRes containerId: Int) {
        val fragment = supportFragmentManager.findFragmentById(containerId)

        if (!checkFragmentOnBackPressed(fragment)) {
            super.onBackPressed()
        }
    }

    private fun checkFragmentOnBackPressed(fragment: Fragment?): Boolean {
        return (fragment != null
                && fragment is BackButtonListener
                && (fragment as BackButtonListener).onBackPressed())
    }
}

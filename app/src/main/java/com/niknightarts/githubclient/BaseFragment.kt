package com.niknightarts.githubclient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.niknightarts.githubclient.utils.BackButtonListener
import moxy.MvpAppCompatFragment

abstract class BaseFragment : MvpAppCompatFragment(), BackButtonListener {
    protected abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onBackPressed(): Boolean {
        return false
    }
}

package com.niknightarts.githubclient.utils

import com.niknightarts.githubclient.ui.repositories.RepositoryListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    object RepositoryListScreen : SupportAppScreen() {
        override fun getFragment() = RepositoryListFragment.newInstance()
    }
}

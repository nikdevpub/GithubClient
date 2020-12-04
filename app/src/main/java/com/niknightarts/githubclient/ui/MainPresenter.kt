package com.niknightarts.githubclient.ui

import androidx.lifecycle.Observer
import com.niknightarts.githubclient.BasePresenter
import com.niknightarts.githubclient.di.MainScope
import com.niknightarts.githubclient.utils.Screens
import moxy.InjectViewState
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@MainScope
@InjectViewState
class MainPresenter @Inject constructor(
    private val router: Router
) : BasePresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.RepositoryListScreen)
    }
}


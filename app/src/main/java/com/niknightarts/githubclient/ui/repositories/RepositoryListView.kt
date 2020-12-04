package com.niknightarts.githubclient.ui.repositories

import com.niknightarts.githubclient.data.network.objects.repo_search.RepositoryItem
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepositoryListView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMessage(message: String)

    fun showProgress(isEnabled: Boolean)

    fun showRepositories(repositoryItems: List<RepositoryItem>?)
}

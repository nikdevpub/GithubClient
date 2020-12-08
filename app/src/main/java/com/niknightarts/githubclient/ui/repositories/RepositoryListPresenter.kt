package com.niknightarts.githubclient.ui.repositories

import com.niknightarts.githubclient.BasePresenter
import com.niknightarts.githubclient.data.network.objects.repo_search.RepositoryItem
import com.niknightarts.githubclient.data.repository.Repository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class RepositoryListPresenter @Inject constructor(
    private val repository: Repository
) :
    BasePresenter<RepositoryListView>() {
    fun searchRepositories(text: String) {
        if (text.isNullOrEmpty()) {
            viewState.showProgress(false)
            viewState.showRepositories(null)
            return
        }

        addDisposable(
            Single.zip(
                repository.getRepositories(text, "stars", "desc", 15, 1)
                    .subscribeOn(Schedulers.newThread()),
                repository.getRepositories(text, "stars", "desc", 15, 2)
                    .subscribeOn(Schedulers.newThread()),
                BiFunction { t1, t2 ->
                    var results = arrayListOf<RepositoryItem>()
                    results.addAll(t1.repositoryItems)
                    results.addAll(t2.repositoryItems)
                    return@BiFunction results
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    viewState.showProgress(true)
                }
                .doAfterTerminate {
                    viewState.showProgress(false)
                }
                .subscribe({ repositories ->
                    viewState.showRepositories(repositories)
                }, { e ->
                    Timber.d(e)
                    viewState.showMessage(e.message ?: "Error")
                })
        )
    }

}

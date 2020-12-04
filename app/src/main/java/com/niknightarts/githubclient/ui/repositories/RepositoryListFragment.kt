package com.niknightarts.githubclient.ui.repositories

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding4.appcompat.queryTextChanges
import com.niknightarts.githubclient.App
import com.niknightarts.githubclient.BaseFragment
import com.niknightarts.githubclient.R
import com.niknightarts.githubclient.data.network.objects.repo_search.RepositoryItem
import com.niknightarts.githubclient.utils.toast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_repository_list.*
import kotlinx.android.synthetic.main.toolbar_template.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class RepositoryListFragment : BaseFragment(), RepositoryListView {
    private lateinit var adapter: RepositoriesAdapter

    @Inject
    @InjectPresenter
    lateinit var presenter: RepositoryListPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private val disposables = CompositeDisposable()

    companion object {
        fun newInstance() = RepositoryListFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }

    override val layoutId = R.layout.fragment_repository_list

    override fun onCreate(savedInstanceState: Bundle?) {
        App.mainComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initRecycler()
        initSwipeRefresh()
    }

    private fun initSwipeRefresh() {
        repositories_list_swipe.setOnRefreshListener {
            val searchView: SearchView =
                toolbar.menu.findItem(R.id.action_search_active).actionView as SearchView
            presenter.searchRepositories(searchView.query.toString())
        }
        repositories_list_swipe.setColorSchemeResources(
            R.color.colorAccent
        )
    }

    private fun initToolbar() {
        toolbar.apply {
            title = context.getString(R.string.repositories_list_title)

            inflateMenu(R.menu.repository_list)
            val searchView: SearchView =
                toolbar.menu.findItem(R.id.action_search_active).actionView as SearchView
            searchView.queryHint = getString(R.string.search)

            disposables.add(
                searchView.queryTextChanges()
                    .skip(1)
                    .debounce(300, TimeUnit.MILLISECONDS)
                    .map { text -> text.toString().trim() }
                    .distinctUntilChanged()
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ text ->
                        Timber.d("search")
                        presenter.searchRepositories(text)
                    }, { e ->
                        Timber.d(e)
                    })
            )
        }
    }

    private fun initRecycler() {
        adapter = RepositoriesAdapter(
            object : RepositoriesAdapter.OnRepositoryItemClickCallback {
                override fun onRepositoryItemClick(id: String) {

                }

            })
        repositories_list_recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        repositories_list_recycler.setHasFixedSize(true)
        repositories_list_recycler.adapter = adapter
    }

    override fun onBackPressed(): Boolean {
        return false
    }

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun showProgress(isEnabled: Boolean) {
        if (isEnabled) {
            repositories_list_recycler.visibility = View.GONE
            repositories_list_progress.visibility = View.VISIBLE
        } else {
            repositories_list_recycler.visibility = View.VISIBLE
            repositories_list_progress.visibility = View.GONE
            repositories_list_swipe.isRefreshing = false
        }
    }

    override fun showRepositories(repositoryItems: List<RepositoryItem>?) {
        adapter.setRepositoryItems(repositoryItems)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }
}

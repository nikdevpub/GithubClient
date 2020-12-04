package com.niknightarts.githubclient.data.repository

import com.niknightarts.githubclient.data.network.GithubApi
import com.niknightarts.githubclient.data.network.objects.repo_search.RepoSearchResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(private val api: GithubApi) : Repository {
    override fun getRepositories(
        searchText: String,
        sort: String,
        order: String,
        perPage: Int,
        page: Int
    ): Single<RepoSearchResponse> {
        return api.getRepositories(searchText, sort, order, perPage, page)
    }
}

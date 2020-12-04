package com.niknightarts.githubclient.data.repository

import com.niknightarts.githubclient.data.network.objects.repo_search.RepoSearchResponse
import io.reactivex.Single

interface Repository {
    fun getRepositories(
        searchText: String,
        sort: String,
        order: String,
        perPage: Int,
        page: Int
    ): Single<RepoSearchResponse>
}

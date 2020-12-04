package com.niknightarts.githubclient.data.network

import com.niknightarts.githubclient.data.network.objects.repo_search.RepoSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET("search/repositories")
    fun getRepositories(
        @Query("q") searchText: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Single<RepoSearchResponse>
}

package com.niknightarts.githubclient.data.network.objects.repo_search

import com.google.gson.annotations.SerializedName

data class RepoSearchResponse(
    val incomplete_results: Boolean,
    @SerializedName("items")
    val repositoryItems: List<RepositoryItem>,
    val total_count: Int
)

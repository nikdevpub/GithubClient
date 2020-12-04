package com.niknightarts.githubclient.utils.ui

interface PaginationCallback {
    val isLastPage: Boolean
    val isNextPageLoading: Boolean

    fun loadNextPage()
}

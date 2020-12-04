package com.niknightarts.githubclient.utils.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.niknightarts.githubclient.utils.ui.PaginationCallback
import timber.log.Timber

class PaginationScrollListener(
    private var layoutManager: LinearLayoutManager,
    private var callback: PaginationCallback
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!callback.isNextPageLoading && !callback.isLastPage) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                callback.loadNextPage()
            }
        }
    }
}

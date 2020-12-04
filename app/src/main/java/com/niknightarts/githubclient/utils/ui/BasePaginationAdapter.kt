package com.niknightarts.githubclient.utils.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BasePaginationAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val ITEM = 0
    private val PROGRESS = 1
    private var isLoadingAdded = false

    var models = ArrayList<T>()

    fun setItems(models: List<T>) {
        this.models.clear()
        this.models.addAll(models)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            ITEM -> getItemViewHolder(parent)
            PROGRESS -> ProgressViewHolder(
                inflater.inflate(getProgressLayoutId(), parent, false)
            )
            else -> throw Exception()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = models[position]

        when (getItemViewType(position)) {
            ITEM -> {
                onBindItem(holder, position)
            }
            PROGRESS -> holder.itemView.setOnClickListener(null)
        }
    }

    abstract fun onBindItem(holder: RecyclerView.ViewHolder, position: Int)

    abstract fun getItemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    override fun getItemCount(): Int {
        return models.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == models.size - 1 && isLoadingAdded) PROGRESS else ITEM
    }

    protected abstract fun getProgressLayoutId(): Int

    protected abstract fun getEmptyModel(): T

    protected class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    /**
     * Helpers
     */

    open fun add(item: T) {
        models.add(item)
        notifyItemInserted(models.size - 1)
    }

    open fun addAll(models: List<T>) {
        for (item in models) {
            add(item)
        }
    }

    open fun remove(item: T?) {
        val position = models.indexOf(item)
        if (position > -1) {
            models.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    open fun clear() {
        isLoadingAdded = false
        while (itemCount > 0) {
            remove(getItem(0))
        }
    }

    fun isEmpty(): Boolean {
        return itemCount == 0
    }

    open fun addLoadingFooter() {
        if (isLoadingAdded) return
        isLoadingAdded = true
        add(getEmptyModel())
    }

    open fun removeLoadingFooter() {
        if (!isLoadingAdded) return
        isLoadingAdded = false

        val position = models.size - 1
        val item = getItem(position)

        if (item != null) {
            models.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun getItem(position: Int): T? {
        return models[position]
    }
}

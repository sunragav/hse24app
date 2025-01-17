package com.sunragav.android_core.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BaseListAdapter<T>(
    itemsSame: (T, T) -> Boolean,
    contentsSame: (T, T) -> Boolean
) : ListAdapter<T, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = itemsSame(oldItem, newItem)
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        contentsSame(oldItem, newItem)
}) {
    var rootRecyclerView: RecyclerView? = null
    abstract val itemLayout: Int

    override fun submitList(list: List<T>?) {
        super.submitList(ArrayList<T>(list ?: listOf()))
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        rootRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        rootRecyclerView = null
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_ITEM -> {
                bindItemViewHolder(holder, position)
            }
            else -> {
                throw(IllegalArgumentException("Unsupported View Type"))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            TYPE_ITEM -> {
                createItemViewHolder(parent)
            }
            else -> {
                throw(IllegalArgumentException("Unsupported View Type"))
            }
        }

    override fun getItemViewType(position: Int) = when {
        isPositionItem(position) -> TYPE_ITEM
        else -> throw IllegalArgumentException("Unsupported View Type")
    }

    open fun createItemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(itemLayout, parent, false)

        return object : RecyclerView.ViewHolder(view) {}
    }

    abstract fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    open fun isPositionItem(position: Int): Boolean = true


    companion object {
        const val TYPE_ITEM = 0
    }
}
package com.sunragav.catalog.views.adpaters

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sunragav.android_core.adapters.BaseListAdapter
import com.sunragav.catalog.R
import com.sunragav.catalog.models.Catalog

class SubCategoriesAdapter : BaseListAdapter<Catalog>(
    itemsSame = { old, new -> old.categoryId == new.categoryId },
    contentsSame = { old, new -> old.title == new.title }
) {
    override val itemLayout: Int
        get() = R.layout.list_item_small_title

    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val catalog = getItem(position)
        holder.itemView.apply {
            val tvSubCatalogTitle by lazy { findViewById<TextView>(R.id.tvSmallCatalogTitle) }
            tvSubCatalogTitle.text = catalog.title
        }
    }
}
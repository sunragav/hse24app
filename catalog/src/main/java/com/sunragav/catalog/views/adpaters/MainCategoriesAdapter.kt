package com.sunragav.catalog.views.adpaters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sunragav.android_core.adapters.BaseListAdapter
import com.sunragav.android_core.extensions.runLayoutAnimation
import com.sunragav.catalog.R
import com.sunragav.catalog.models.Catalog
import kotlinx.android.synthetic.main.catalog_categories_list.view.*

class MainCategoriesAdapter : BaseListAdapter<Catalog>(
    itemsSame = { old, new -> old.categoryId == new.categoryId },
    contentsSame = { old, new -> old.title == new.title }
) {
    private var prevRecyclerView: RecyclerView? = null
    override val itemLayout: Int
        get() = R.layout.catalog_categories_list

    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val catalog = getItem(position)
        holder.itemView.apply {
            val tvMainCatalogTitle by lazy { findViewById<TextView>(R.id.tvBigCatalogTitle) }
            tvMainCatalogTitle.text = catalog.title
            setOnClickListener {
                prevRecyclerView?.visibility = View.GONE
                prevRecyclerView = rvCategories.apply {
                    layoutManager = GridLayoutManager(context, 1)
                    adapter = SubCategoriesAdapter().also {
                        it.submitList(catalog.subCatalog)
                    }
                    visibility = if (visibility == View.GONE) {
                        runLayoutAnimation()
                        View.VISIBLE
                    } else
                        View.GONE
                }
            }
        }
    }
}
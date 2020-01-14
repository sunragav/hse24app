package com.sunragav.catalog.views.adpaters

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.sunragav.android_core.adapters.BaseListAdapter
import com.sunragav.android_core.extensions.runLayoutAnimation
import com.sunragav.catalog.R
import com.sunragav.catalog.models.Catalog

class SubCategoriesAdapter : BaseListAdapter<Catalog>(
    itemsSame = { old, new -> old.categoryId == new.categoryId },
    contentsSame = { old, new -> old.title == new.title }
) {
    private var prevRecyclerView: RecyclerView? = null
    private var scrollPosX = -1
    private var scrollPosY = -1
    override val itemLayout: Int
        get() = R.layout.sub_catalog_categories_list

    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val catalog = getItem(position)
        holder.itemView.apply {
            val catalogContainer by lazy { findViewById<MaterialCardView>(R.id.subCatalogContainer) }
            val tvCatalogTitle by lazy { findViewById<TextView>(R.id.tvSmallCatalogTitle) }
            val rvCategories by lazy { findViewById<RecyclerView>(R.id.rvTailCategories) }
            val btnCategoryExpand by lazy { findViewById<ImageButton>(R.id.btnCategoryExpand) }
            tvCatalogTitle.text = catalog.title

            if (catalog.subCatalog.isEmpty()) btnCategoryExpand.visibility = View.GONE
            else btnCategoryExpand.visibility = View.VISIBLE
            rvCategories.also { rv ->
                rv.layoutManager = GridLayoutManager(context, 1)
                rv.adapter = TailCategoriesAdapter().also {
                    it.submitList(catalog.subCatalog)
                }
            }
            catalogContainer.setOnClickListener {
                if (scrollPosX != -1) scrollTo(scrollPosX, scrollPosY)
                if (prevRecyclerView != rvCategories) prevRecyclerView?.visibility = View.GONE
                prevRecyclerView = rvCategories.also { rv ->
                    rv.visibility = if (rv.visibility == View.GONE) {
                        rv.runLayoutAnimation()
                        View.VISIBLE
                    } else
                        View.GONE
                    scrollPosX = scrollX
                    scrollPosY = scrollY
                }

            }
        }
    }
}
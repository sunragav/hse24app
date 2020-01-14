package com.sunragav.catalog.views.adpaters

import android.net.Uri
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.sunragav.android_core.adapters.BaseListAdapter
import com.sunragav.android_core.deeplinks.DeepLinks
import com.sunragav.android_core.deeplinks.navigateUriWithDefaultOptions
import com.sunragav.android_core.extensions.runLayoutAnimation
import com.sunragav.catalog.R
import com.sunragav.catalog.models.Catalog

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
            val catalogContainer by lazy { findViewById<MaterialCardView>(R.id.mainCatalogContainer) }
            val tvCatalogTitle by lazy { findViewById<TextView>(R.id.tvBigCatalogTitle) }
            val rvCategories by lazy { findViewById<RecyclerView>(R.id.rvSubCategories) }
            val btnCategoryExpand by lazy { findViewById<ImageButton>(R.id.btnCategoryExpand) }
            tvCatalogTitle.text = catalog.title

            btnCategoryExpand.visibility =
                if (catalog.subCatalog.isEmpty()) View.GONE else View.VISIBLE
            rvCategories.also { rv ->
                rv.layoutManager = GridLayoutManager(context, 1)
                rv.adapter = SubCategoriesAdapter().also {
                    it.submitList(catalog.subCatalog)
                }
            }
            catalogContainer.setOnClickListener {
                if (catalog.subCatalog.isEmpty()) {
                    it.findNavController()
                        .navigateUriWithDefaultOptions(Uri.parse("${DeepLinks.PRODUCTS}/${catalog.categoryId}"))
                } else {
                    if (prevRecyclerView != rvCategories) prevRecyclerView?.visibility = View.GONE
                    prevRecyclerView = rvCategories.also { rv ->
                        rv.visibility = if (rv.visibility == View.GONE) {
                            rv.runLayoutAnimation()
                            View.VISIBLE
                        } else
                            View.GONE
                        rootRecyclerView?.layoutManager?.scrollToPosition(position)
                    }
                }
            }
        }
    }
}
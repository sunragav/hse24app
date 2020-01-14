package com.sunragav.catalog.views.adpaters

import android.net.Uri
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.sunragav.android_core.adapters.BaseListAdapter
import com.sunragav.android_core.deeplinks.DeepLinks
import com.sunragav.android_core.deeplinks.navigateUriWithDefaultOptions
import com.sunragav.catalog.R
import com.sunragav.catalog.models.Catalog

class TailCategoriesAdapter : BaseListAdapter<Catalog>(
    itemsSame = { old, new -> old.categoryId == new.categoryId },
    contentsSame = { old, new -> old.title == new.title }
) {
    override val itemLayout: Int
        get() = R.layout.tail_catalog_categories

    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val catalog = getItem(position)
        holder.itemView.apply {
            val tailCatalogContainer by lazy { findViewById<MaterialCardView>(R.id.tailCatalogContainer) }
            val tvSubCatalogTitle by lazy { findViewById<TextView>(R.id.tvVerySmallCatalogTitle) }
            tvSubCatalogTitle.text = catalog.title
            tailCatalogContainer.setOnClickListener {
                it.findNavController()
                    .navigateUriWithDefaultOptions(Uri.parse("${DeepLinks.PRODUCTS}/${catalog.categoryId}"))
            }
        }
    }
}
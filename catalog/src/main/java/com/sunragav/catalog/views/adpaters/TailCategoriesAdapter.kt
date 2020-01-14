package com.sunragav.catalog.views.adpaters

import android.view.View
import com.sunragav.catalog.R

class TailCategoriesAdapter : AbstractCategoriesAdapter() {


    override fun initViewsAndAdapter(root: View) {
        catalogContainer = root.findViewById(R.id.tailCatalogContainer)
        tvCatalogTitle = root.findViewById(R.id.tvVerySmallCatalogTitle)
    }

    override val itemLayout: Int
        get() = R.layout.tail_catalog_categories

}
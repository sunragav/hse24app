package com.sunragav.catalog.views.adpaters

import android.view.View
import com.sunragav.catalog.R

class SubCategoriesAdapter : AbstractCategoriesAdapter() {

    override fun initViewsAndAdapter(root: View) {
        catalogContainer = root.findViewById(R.id.subCatalogContainer)
        tvCatalogTitle = root.findViewById(R.id.tvSmallCatalogTitle)
        rvCategories = root.findViewById(R.id.rvTailCategories)
        subCategoriesAdapter = TailCategoriesAdapter()
    }

    override val itemLayout: Int
        get() = R.layout.sub_catalog_categories_list

}
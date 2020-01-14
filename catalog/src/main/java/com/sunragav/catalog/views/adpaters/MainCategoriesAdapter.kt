package com.sunragav.catalog.views.adpaters

import android.view.View
import com.sunragav.catalog.R

class MainCategoriesAdapter : AbstractCategoriesAdapter() {
    override fun initViewsAndAdapter(root: View) {
        catalogContainer = root.findViewById(R.id.mainCatalogContainer)
        tvCatalogTitle = root.findViewById(R.id.tvBigCatalogTitle)
        rvCategories = root.findViewById(R.id.rvSubCategories)
        subCategoriesAdapter = SubCategoriesAdapter()
    }

    override val itemLayout: Int
        get() = R.layout.catalog_categories_list
}
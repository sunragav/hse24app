package com.sunragav.catalog.models

data class Catalog(
    val categoryId: Int,
    val title: String,
    val subCatalog: List<Catalog>
)
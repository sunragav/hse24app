package com.sunragav.data.remote.models


data class CatalogModel(
    val categoryId: Int,
    val displayName: String,
    val children: List<CatalogModel>
)
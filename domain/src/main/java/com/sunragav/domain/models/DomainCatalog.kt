package com.sunragav.domain.models

data class DomainCatalog(
    val categoryId: Int,
    val title: String,
    val subCatalog: List<DomainCatalog>
)
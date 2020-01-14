package com.sunragav.domain.models

data class DomainCatalog(
    val categoryId: Int = -1,
    val title: String = "",
    val subCatalog: List<DomainCatalog> = emptyList()
)
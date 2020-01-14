package com.sunragav.catalog.models

import com.sunragav.domain.models.DomainCatalog
import javax.inject.Inject

class DomainUIModelMapper @Inject constructor() {
    fun toUi(domain: DomainCatalog): Catalog {
        return Catalog(
            categoryId = domain.categoryId,
            title = domain.title,
            subCatalog = domain.subCatalog.map { toUi(it) }
        )
    }
}
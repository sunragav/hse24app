package com.sunragav.data.remote.mapper

import com.sunragav.data.remote.models.CatalogModel
import com.sunragav.domain.models.DomainCatalog
import javax.inject.Inject

class RemoteToDomainMapper @Inject constructor() {
    fun toDomain(remote: CatalogModel?): DomainCatalog {
        return if (remote != null)
            DomainCatalog(
                categoryId = remote.categoryId,
                title = remote.displayName,
                subCatalog = remote.children.map { toDomain(it) })
        else DomainCatalog()
    }
}
package com.sunragav.data.remote.catalog

import com.sunragav.domain.models.DomainCatalog

interface RemoteCatalogSource {
    suspend fun getCatalog(): List<DomainCatalog>
}
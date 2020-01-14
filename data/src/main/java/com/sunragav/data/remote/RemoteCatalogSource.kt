package com.sunragav.data.remote

import com.sunragav.domain.models.DomainCatalog

interface RemoteCatalogSource {
    suspend fun getCatalog(): List<DomainCatalog>
}
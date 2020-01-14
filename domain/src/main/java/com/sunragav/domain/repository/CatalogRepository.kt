package com.sunragav.domain.repository

import com.sunragav.domain.models.DomainCatalog

interface CatalogRepository {
    suspend fun getCatalog(): List<DomainCatalog>
}
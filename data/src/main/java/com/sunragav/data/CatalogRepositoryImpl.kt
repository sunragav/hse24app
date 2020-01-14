package com.sunragav.data

import com.sunragav.data.remote.RemoteCatalogSource
import com.sunragav.domain.models.DomainCatalog
import com.sunragav.domain.repository.CatalogRepository
import javax.inject.Inject

class CatalogRepositoryImpl @Inject constructor(private val remoteRepository: RemoteCatalogSource) :
    CatalogRepository {
    override suspend fun getCatalog(): List<DomainCatalog> {
        return remoteRepository.getCatalog()
    }
}
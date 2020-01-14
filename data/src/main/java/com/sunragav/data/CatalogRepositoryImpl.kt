package com.sunragav.data

import com.sunragav.data.remote.RemoteCatalogSource
import com.sunragav.domain.models.DomainCatalog
import com.sunragav.domain.repository.CatalogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CatalogRepositoryImpl @Inject constructor(private val remoteRepository: RemoteCatalogSource) :
    CatalogRepository {
    override suspend fun getCatalog(): List<DomainCatalog> = withContext(Dispatchers.IO) {
        remoteRepository.getCatalog()
    }
}
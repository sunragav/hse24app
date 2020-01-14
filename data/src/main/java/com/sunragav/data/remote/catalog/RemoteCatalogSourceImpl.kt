package com.sunragav.data.remote.catalog

import com.sunragav.data.remote.mapper.DomainRemoteModelMapper
import com.sunragav.domain.models.DomainCatalog
import javax.inject.Inject

class RemoteCatalogSourceImpl @Inject constructor(
    private val domainRemoteModelMapper: DomainRemoteModelMapper,
    private val remoteCatalogService: RemoteCatalogService
) : RemoteCatalogSource {
    override suspend fun getCatalog(): List<DomainCatalog> {
        return remoteCatalogService.getCatalog().children.map {
            domainRemoteModelMapper.toDomain(it)
        }
    }
}
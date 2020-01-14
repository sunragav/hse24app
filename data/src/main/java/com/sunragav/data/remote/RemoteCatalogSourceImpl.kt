package com.sunragav.data.remote

import com.sunragav.data.remote.mapper.RemoteToDomainMapper
import com.sunragav.domain.models.DomainCatalog
import javax.inject.Inject

class RemoteCatalogSourceImpl @Inject constructor(
    private val remoteToDomainMapper: RemoteToDomainMapper,
    private val remoteCatalogService: RemoteCatalogService
) : RemoteCatalogSource {
    override suspend fun getCatalog(): List<DomainCatalog> {
        return remoteCatalogService.getCatalog().map { remoteToDomainMapper.toDomain(it) }
    }
}
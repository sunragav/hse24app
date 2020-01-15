package com.sunragav.data.remote.pdp

import com.sunragav.data.remote.mapper.DomainRemoteModelMapper
import com.sunragav.domain.models.DomainPDP
import javax.inject.Inject

class RemotePDPSourceImpl @Inject constructor(
    private val domainRemoteModelMapper: DomainRemoteModelMapper,
    private val remotePDPService: RemotePDPService
) : RemotePDPSource {

    override suspend fun getPDP(sku: String): DomainPDP {
        return remotePDPService.getPDP(sku).let {
            domainRemoteModelMapper.toDomain(it)
        }
    }
}
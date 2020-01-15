package com.sunragav.data.pdp

import com.sunragav.data.remote.pdp.RemotePDPSource
import com.sunragav.domain.models.DomainPDP
import com.sunragav.domain.repository.PDPRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PDPRepositoryImpl @Inject constructor(private val pdpSource: RemotePDPSource) :
    PDPRepository {
    override suspend fun getPDP(sku: String): DomainPDP =
        withContext(Dispatchers.IO) {
            pdpSource.getPDP(sku)
        }
}
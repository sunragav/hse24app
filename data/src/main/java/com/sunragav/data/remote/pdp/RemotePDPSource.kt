package com.sunragav.data.remote.pdp

import com.sunragav.domain.models.DomainPDP

interface RemotePDPSource {
    suspend fun getPDP(sku: String): DomainPDP
}
package com.sunragav.domain.repository

import com.sunragav.domain.models.DomainPDP

interface PDPRepository {
    suspend fun getPDP(sku: String): DomainPDP
}
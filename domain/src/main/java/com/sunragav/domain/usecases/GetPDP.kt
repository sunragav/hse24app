package com.sunragav.domain.usecases

import com.sunragav.domain.repository.PDPRepository
import javax.inject.Inject

class GetPDP @Inject constructor(
    private val pdpRepository: PDPRepository
) {
    suspend operator fun invoke(sku: String) = pdpRepository.getPDP(sku)
}
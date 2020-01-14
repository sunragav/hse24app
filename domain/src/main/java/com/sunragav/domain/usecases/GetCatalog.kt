package com.sunragav.domain.usecases

import com.sunragav.domain.repository.CatalogRepository
import javax.inject.Inject

class GetCatalog @Inject constructor(
    private val catalogRepository: CatalogRepository
) {
    suspend operator fun invoke() = catalogRepository.getCatalog()
}
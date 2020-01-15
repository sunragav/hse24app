package com.sunragav.data.remote.products

import com.sunragav.data.remote.mapper.DomainRemoteModelMapper
import com.sunragav.domain.models.DomainProduct
import javax.inject.Inject

class RemoteProductsSourceImpl @Inject constructor(
    private val domainRemoteModelMapper: DomainRemoteModelMapper,
    private val remoteProductsService: RemoteProductsService
) : RemoteProductsSource {
    companion object {
        const val URL_ENCODING_PREFIX = "%3F"
        const val URL_ENCODING_EQUAL_TO = "%3D"
        const val PAGE = "page"
        const val URL_CATEGORY_PREFIX = "*-"
    }

    override suspend fun getProducts(categoryId: Int, page: Int): List<DomainProduct> {
        return remoteProductsService.getCatalog(
            getEncodedCategoryId(categoryId),
            getEncodedPage(page)
        ).productResults.map {
            domainRemoteModelMapper.toDomain(it)
        }
    }

    private fun getEncodedPage(page: Int) = "$URL_ENCODING_PREFIX$PAGE$URL_ENCODING_EQUAL_TO$page"
    private fun getEncodedCategoryId(categoryId: Int) = "$URL_CATEGORY_PREFIX$categoryId"

}
package com.sunragav.data.remote.mapper

import com.sunragav.data.remote.models.CatalogModel
import com.sunragav.data.remote.models.ProductResult
import com.sunragav.data.remote.qualifiers.ImgBaseUrl
import com.sunragav.domain.models.DomainCatalog
import com.sunragav.domain.models.DomainProduct
import javax.inject.Inject

class DomainRemoteModelMapper @Inject constructor(@ImgBaseUrl private val IMAGE_URL: String) {
    companion object {
        const val PIC_FILE = "_pics480.jpg"
    }
    fun toDomain(remote: CatalogModel): DomainCatalog {
        return DomainCatalog(
            categoryId = remote.categoryId,
            title = remote.displayName,
            subCatalog = remote.children.map { toDomain(it) })
    }

    fun toDomain(remote: ProductResult): DomainProduct {
        return DomainProduct(
            sku = remote.sku,
            nameShort = remote.nameShort,
            brandNameLong = remote.brandNameLong,
            productPrice = with(remote.productPrice) { "$price $currency" },
            averageStars = remote.averageStars,
            imageUris = remote.imageUris.map { "$IMAGE_URL$it$PIC_FILE" }
        )
    }
}
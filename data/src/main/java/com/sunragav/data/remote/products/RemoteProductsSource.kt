package com.sunragav.data.remote.products

import com.sunragav.domain.models.DomainProduct

interface RemoteProductsSource {
    suspend fun getProducts(categoryId: Int, page: Int): List<DomainProduct>
}
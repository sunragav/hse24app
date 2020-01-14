package com.sunragav.domain.repository

import com.sunragav.domain.models.DomainProduct

interface ProductsRepository {
    suspend fun getProducts(categoryId: Int, page: Int): List<DomainProduct>
}
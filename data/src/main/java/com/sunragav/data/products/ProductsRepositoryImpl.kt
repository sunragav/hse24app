package com.sunragav.data.products

import com.sunragav.data.remote.RemoteProductsSource
import com.sunragav.domain.models.DomainProduct
import com.sunragav.domain.repository.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(private val remoteProductsSource: RemoteProductsSource) :
    ProductsRepository {
    override suspend fun getProducts(categoryId: Int, page: Int): List<DomainProduct> =
        withContext(Dispatchers.IO) {
            remoteProductsSource.getProducts(categoryId, page)
        }
}
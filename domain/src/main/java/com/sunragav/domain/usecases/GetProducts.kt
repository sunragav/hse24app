package com.sunragav.domain.usecases

import com.sunragav.domain.repository.ProductsRepository
import javax.inject.Inject

class GetProducts @Inject constructor(private val productsRepository: ProductsRepository) {
    suspend operator fun invoke(categoryId: Int, page: Int) =
        productsRepository.getProducts(categoryId, page)
}
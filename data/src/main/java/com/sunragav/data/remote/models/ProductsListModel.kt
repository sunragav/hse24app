package com.sunragav.data.remote.models


data class ProductResult(
    val sku: String,
    val nameShort: String,
    val brandNameLong: String,
    val productPrice: ProductPrice,
    val averageStars: Int,
    val imageUris: List<String>
)

data class ProductResultWrapper(
    val resultCount: Int,
    val productResults: List<ProductResult>
)

data class ProductPrice(
    val price: Float,
    val currency: String
)


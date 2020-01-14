package com.sunragav.data.remote.models


data class ProductResult(
    val sku: String,
    val nameShort: String,
    val brandNameLong: String,
    val productPrice: ProductPrice,
    val averageStars: Int,
    val imageUris: List<String>
)

data class ProductResultWrapper<out T>(
    val resultCount: Int,
    val productResults: T
)

data class ProductPrice(
    val price: Int,
    val currency: String
)


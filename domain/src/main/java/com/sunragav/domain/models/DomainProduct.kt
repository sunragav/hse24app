package com.sunragav.domain.models

data class DomainProduct(
    val sku: String,
    val nameShort: String,
    val brandNameLong: String,
    val productPrice: String,
    val averageStars: Int,
    val imageUris: List<String>
)

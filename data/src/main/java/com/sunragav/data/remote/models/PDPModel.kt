package com.sunragav.data.remote.models

data class PDPModel(
    val sku: String,
    val imageUris: List<String>,
    val title: String,
    val nameShort: String,
    val longDescription: String,
    val usps: List<String>,
    val averageStars: Int,
    val productPrice: ProductPrice
)


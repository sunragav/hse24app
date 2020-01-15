package com.sunragav.domain.models

data class DomainPDP(
    val sku: String,
    val imageUris: List<String>,
    val title: String,
    val brandNameLong: String,
    val nameShort: String,
    val longDescription: String,
    val usps: List<String>,
    val averageStars: Int,
    val productPrice: String
)
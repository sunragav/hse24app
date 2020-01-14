package com.sunragav.data.remote

import com.sunragav.data.remote.models.ProductResult
import com.sunragav.data.remote.models.ProductResultWrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteProductsService {
    @GET("c/**/{category}/{query}")
    suspend fun getCatalog(
        @Path("category") category: String,
        @Path("query") query: String
    ): ProductResultWrapper<List<ProductResult>>
}
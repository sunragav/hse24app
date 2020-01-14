package com.sunragav.data.remote

import com.sunragav.data.remote.models.CatalogWrapper
import retrofit2.http.GET

interface RemoteCatalogService {
    @GET("category/tree")
    suspend fun getCatalog(): CatalogWrapper
}
package com.sunragav.data.remote.pdp

import com.sunragav.data.remote.models.PDPModel
import retrofit2.http.GET
import retrofit2.http.Path

interface RemotePDPService {
    @GET("product/{sku}")
    suspend fun getPDP(
        @Path("sku") sku: String
    ): PDPModel
}
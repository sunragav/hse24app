package com.sunragav.data.remote.interceptor

import com.sunragav.data.remote.qualifiers.AppDevice
import com.sunragav.data.remote.qualifiers.Locale
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiInterceptor @Inject constructor(
    @AppDevice private val appDevice: String,
    @Locale private val locale: String
) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .addHeader("appDevice", appDevice)
            .addHeader("locale", locale)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
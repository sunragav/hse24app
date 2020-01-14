package com.sunragav.hse24app.di

import com.squareup.moshi.Moshi
import com.sunragav.data.catalog.CatalogRepositoryImpl
import com.sunragav.data.products.ProductsRepositoryImpl
import com.sunragav.data.remote.RemoteProductsService
import com.sunragav.data.remote.catalog.RemoteCatalogService
import com.sunragav.data.remote.catalog.RemoteCatalogSource
import com.sunragav.data.remote.catalog.RemoteCatalogSourceImpl
import com.sunragav.data.remote.interceptor.ApiInterceptor
import com.sunragav.data.remote.qualifiers.AppDevice
import com.sunragav.data.remote.qualifiers.ImgBaseUrl
import com.sunragav.data.remote.qualifiers.Locale
import com.sunragav.domain.repository.CatalogRepository
import com.sunragav.domain.repository.ProductsRepository
import com.sunragav.hse24app.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module(includes = [DataModule.Binders::class])
class DataModule {

    @Module
    interface Binders {
        @Binds
        fun bindsCatalogRepository(
            repoImpl: CatalogRepositoryImpl
        ): CatalogRepository

        @Binds
        fun bindsRemoteRepository(
            remoteDataSourceImpl: RemoteCatalogSourceImpl
        ): RemoteCatalogSource

        @Binds
        fun bindsProductRepository(
            repoImpl: ProductsRepositoryImpl
        ): ProductsRepository
    }

    @Provides
    @Locale
    fun provideLocale() = BuildConfig.LOCALE

    @Provides
    @AppDevice
    fun provideAppDevice() = BuildConfig.APP_DEVICE

    @Provides
    @ImgBaseUrl
    fun provideImgBaseUrl() = BuildConfig.IMG_BASE_URL


    @Provides
    @Singleton
    fun provideCatalogService(retrofit: Retrofit): RemoteCatalogService =
        retrofit.create(RemoteCatalogService::class.java)


    @Provides
    @Singleton
    fun provideProductsService(retrofit: Retrofit): RemoteProductsService =
        retrofit.create(RemoteProductsService::class.java)


    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .build()

    @Provides
    @Singleton
    fun provideHttpClient(apiInterceptor: ApiInterceptor): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val level = getInterceptorLevel()
        httpLoggingInterceptor.level = level
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(apiInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS).build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    private fun getInterceptorLevel(): HttpLoggingInterceptor.Level? {
        return if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }

}
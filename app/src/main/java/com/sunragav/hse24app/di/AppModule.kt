package com.sunragav.hse24app.di

import android.app.Application
import android.content.Context
import com.sunragav.catalog.views.CatalogFragment
import com.sunragav.hse24app.views.MainActivity
import com.sunragav.hse24app.views.SplashFragment
import com.sunragav.products.views.ProductsFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {
    @Binds
    abstract fun bindContext(application: Application): Context

    @ContributesAndroidInjector
    internal abstract fun contributesMainActivity(): MainActivity


    @ContributesAndroidInjector
    internal abstract fun contributesSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    internal abstract fun contributesCatalogFragment(): CatalogFragment

    @ContributesAndroidInjector
    internal abstract fun contributesProductsFragment(): ProductsFragment
}